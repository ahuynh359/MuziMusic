package com.team15.muzimusic.ui.player.songlyrics

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.team15.muzimusic.adapter.EventBusModel
import com.team15.muzimusic.adapter.LyricAdapter
import com.team15.muzimusic.adapter.LyricsClickListener
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.LineLyric
import com.team15.muzimusic.data.models.convertToLineLyric
import com.team15.muzimusic.databinding.FragmentSongLyricsBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.player.PlayerViewModel
import com.team15.muzimusic.ui.player.songlyrics.CenterLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.util.ArrayList

@AndroidEntryPoint
class SongLyricsFragment : Fragment(), LyricsClickListener {

    private lateinit var binding: FragmentSongLyricsBinding

    private val viewModel by activityViewModels<PlayerViewModel>()

    private lateinit var lyricAdapter: LyricAdapter
    private lateinit var centerLayoutManager: CenterLayoutManager

    private var currentLine: Int = -1;
    private var songLyrics: ArrayList<LineLyric> = arrayListOf()

    private var scrollJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongLyricsBinding.inflate(layoutInflater, container, false)

        lyricAdapter = LyricAdapter(songLyrics, requireContext(), this)
        centerLayoutManager = CenterLayoutManager(context)

        binding.recyclerLyrics.apply {
            adapter = lyricAdapter
            layoutManager = centerLayoutManager
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.song.observe(requireActivity()) {
            songLyrics = getSongLyrics(it.lyrics)
            lyricAdapter.setData(songLyrics)
            binding.recyclerLyrics.adapter = lyricAdapter
        }

        viewModel.currentSongTime.observe(viewLifecycleOwner) { time ->
            binding.recyclerLyrics.post {
                if (viewModel.isUserTouchedSlider) {
                    scrollLyrics(time)
                } else {
                    smartScrollLyric(time)
                }
            }
        }

    }

    /*
     * Scroll đén dòng lyric hiện tại
     */
    private fun scrollLyrics(time: Int) {
        val indexLine = indexLine(time, songLyrics)

        if (indexLine != currentLine && indexLine >= 0 && indexLine < songLyrics.size) {
            lyricAdapter.currentLine(indexLine)

            // Scroll đến dòng hiện tại
            binding.recyclerLyrics.smoothScrollToPosition(indexLine)
            binding.tvLyricsTop.visibility = View.GONE

            currentLine = indexLine

            if (scrollJob?.isActive == true) scrollJob?.cancel()
            scrollJob = MainScope().launch {
                delay(1000)
                viewModel.isUserTouchedSlider = false
                cancel()
            }
            scrollJob?.start()
        }
    }

    /*
     * Scroll đến dòng lyric hiện tại nếu dòng đó đang được hiển thị trên màn hình
     * Nếu dòng đó không hiển thị thì sẽ hiển thị tvLyricsTop
     */
    private fun smartScrollLyric(time: Int) {
        val indexLine = indexLine(time, songLyrics)

        if (indexLine != currentLine && indexLine >= 0 && indexLine < songLyrics.size) {
            lyricAdapter.currentLine(indexLine)

            // Nếu nằm ngoài danh sách các item lyric đang hiển thị trên màn hình
            // thì ta hiển thị dòng lyric hiện tại ở trên cùng
            // Ngược lại, smoothScroll tới vị trí lyric hiện tại và ẩn dòng lyric top
            if (indexLine < centerLayoutManager.findFirstVisibleItemPosition() ||
                indexLine > centerLayoutManager.findLastVisibleItemPosition()
            ) {
                binding.tvLyricsTop.text = songLyrics[indexLine].text
                binding.tvLyricsTop.visibility = View.VISIBLE
            } else {
                binding.recyclerLyrics.smoothScrollToPosition(indexLine)
                binding.tvLyricsTop.visibility = View.GONE
            }

            currentLine = indexLine
        }
    }

    private fun indexLine(time: Int, lyrics: ArrayList<LineLyric>): Int {
        var l = 0
        var r = lyrics.size - 1

        while (l <= r) {
            val m = (l + r) / 2
            if (time < lyrics[m].startTime) {
                r = m - 1
            } else {
                if (m + 1 < lyrics.size) {
                    if (time < lyrics[m + 1].startTime) {
                        return m
                    } else {
                        l = m + 1
                    }
                } else {
                    return m
                }
            }
        }
        return -1
    }

    private fun getSongLyrics(text: String): ArrayList<LineLyric> {
        val lyrics = arrayListOf<LineLyric>()

        if(text.isEmpty()){
            lyrics.add(LineLyric(0, "Chưa có lời bài hát"))
        }else{
            lyrics.add(LineLyric(0, "..."))

            val list = text.split('\n') as ArrayList<String>
            for (line in list) {
                lyrics.add(line.convertToLineLyric())
            }
        }

        return lyrics
    }

    override fun onLineLyricClick(lineLyric: LineLyric) {
        EventBus.getDefault().post(EventBusModel.MusicTimeSeekEvent((lineLyric.startTime.toLong())))

        if (viewModel.isPlaying.value == false) {
            Intent(requireContext(), MusicService::class.java).apply {
                putExtra(Constants.Action, MusicService.ACTION_PLAY)
            }.also {
                Helper.startMusicService(requireContext(), it)
            }

        }
    }

}
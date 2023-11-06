package com.team15.muzimusic.ui.home.library.mysongs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.team15.muzimusic.adapter.SongClickListener
import com.team15.muzimusic.adapter.SongLiteAdapter
import com.team15.muzimusic.base.fragments.BaseDialogFragment
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.databinding.FragmentMySongsBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.formsong.FormSongActivity
import com.team15.muzimusic.ui.menubottom.MenuBottomFragment
import com.team15.muzimusic.ui.player.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MySongsFragment : BaseDialogFragment(), SongClickListener {



    private lateinit var binding: FragmentMySongsBinding
    private val viewModel by viewModels<MySongsViewModel>()

    private lateinit var songAdapter: SongLiteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    // Lấy dữ liêu tu API ve
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Găn giao dien vao de xu li
        binding = FragmentMySongsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongLiteAdapter(this)

        // Gan adapter vao list my song
        binding.recyclerSong.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(this@MySongsFragment.context)
        }

        // Progress bar
        viewModel.isLoading.observe(this) {
            binding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE
        }

        // Check khi co song va ko co song nao
        viewModel.mySongs.observe(viewLifecycleOwner) {
            songAdapter.differ.submitList(it)
            if (it.isNotEmpty()) {
                binding.recyclerSong.visibility = View.VISIBLE
                binding.tvAlert.visibility = View.GONE
            } else {
                binding.recyclerSong.visibility = View.GONE
                binding.tvAlert.visibility = View.VISIBLE
            }
        }

        // Add song -> chuyen qua activity
        binding.btnAddSong.setOnClickListener {
            startActivity(Intent(context, FormSongActivity::class.java))
        }

        // Bam vao nut play
        binding.btnPlayMusic.setOnClickListener {
            viewModel.mySongs.value?.let {
                if (it.isNotEmpty()) {
                    startActivity(Intent(context, PlayerActivity::class.java))
                    Helper.sendMusicAction(
                        requireContext(),
                        MusicService.ACTION_PLAY,
                        it[0],
                        it as ArrayList<Song>
                    )
                }
            }
        }
    }

    //Click len music
    override fun onSongClick(song: Song) {
        startActivity(Intent(context, PlayerActivity::class.java))
        Helper.sendMusicAction(
            requireContext(),
            MusicService.ACTION_PLAY,
            song,
            viewModel.mySongs.value as ArrayList<Song>
        )
    }

    // Mở menu
    override fun onOpenMenu(song: Song, position: Int) {
        MenuBottomFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Song, song)
            }
        }.show(requireActivity().supportFragmentManager, null)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }

}
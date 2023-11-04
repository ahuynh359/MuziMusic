package com.team15.muzimusic.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.team15.muzimusic.adapter.SongClickListener
import com.team15.muzimusic.adapter.SongLiteAdapter
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.databinding.FragmentLabelSearchBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.menubottom.MenuBottomFragment
import com.team15.muzimusic.ui.player.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LabelSearchFragment : Fragment(), SongClickListener {

    private lateinit var binding: FragmentLabelSearchBinding
    private val viewModel by viewModels<SearchViewModel>({ requireActivity() })

    private lateinit var songAdapter: SongLiteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLabelSearchBinding.inflate(inflater, container, false)

        viewModel.labels.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.tvLabel.text = "Ko có label trong db"
            } else {
                binding.tvLabel.text = it.result[0].label
            }






        }

        songAdapter = SongLiteAdapter(this)
        binding.recyclerView.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.songType.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.tvNoResult.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {


                songAdapter.differ.submitList(it)

                binding.tvNoResult.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    override fun onSongClick(song: Song) {
        viewModel.songs.value?.let {
            startActivity(Intent(context, PlayerActivity::class.java))
            Helper.sendMusicAction(
                requireContext(),
                MusicService.ACTION_PLAY,
                song,
                ArrayList(it)
            )
        }
    }

    override fun onOpenMenu(song: Song, position: Int) {
        MenuBottomFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Song, song)
                putInt(Constants.Position, position)
            }
        }.show(requireActivity().supportFragmentManager, null)
    }

}



package com.team15.muzimusic.ui.home.library.favoritesongs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team15.muzimusic.adapter.SongClickListener
import com.team15.muzimusic.adapter.SongLiteAdapter
import com.team15.muzimusic.base.fragments.BaseDialogFragment
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.databinding.FragmentFavoriteSongsBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.menubottom.MenuBottomFragment
import com.team15.muzimusic.ui.menubottom.MenuBottomViewModel
import com.team15.muzimusic.ui.player.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteSongsFragment : BaseDialogFragment(), SongClickListener {

//    override val isFullHeight: Boolean = true

    private lateinit var binding: FragmentFavoriteSongsBinding
    private val viewModel by viewModels<FavoriteSongsViewModel>()
    private val menuViewModel by viewModels<MenuBottomViewModel>({ requireActivity() })

    private lateinit var songAdapter: SongLiteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchData()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteSongsBinding.inflate(inflater, container, false)
        observersMenuEvent()
        return binding.root
    }

    private fun observersMenuEvent() {
        menuViewModel.actionLoveStatus.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {

                } else {

                }
                Toast.makeText(context, menuViewModel.message, Toast.LENGTH_SHORT).show()
                menuViewModel.actionLoveStatus.value = null
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongLiteAdapter(this)
        binding.recyclerSong.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(this@FavoriteSongsFragment.context)
        }

        viewModel.favoriteSongs.observe(viewLifecycleOwner) {
            songAdapter.differ.submitList(it)
            if (it.isNotEmpty()) {
                binding.recyclerSong.visibility = View.VISIBLE
                binding.tvAlert.visibility = View.GONE
            } else {
                binding.recyclerSong.visibility = View.GONE
                binding.tvAlert.visibility = View.VISIBLE
            }
        }

        viewModel.isLoading.observe(this) {
            if (it)
                binding.pbLoading.visibility = View.VISIBLE
            else
                binding.pbLoading.visibility = View.GONE
        }

        binding.recyclerSong.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.isLoading.value?.let {
                        if (!it) viewModel.fetchData()
                    }
                }
            }
        })

        binding.btnPlayMusic.setOnClickListener {
            viewModel.favoriteSongs.value?.let {
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

    override fun onSongClick(song: Song) {
        startActivity(Intent(context, PlayerActivity::class.java))
        Helper.sendMusicAction(
            requireContext(),
            MusicService.ACTION_PLAY,
            song,
            viewModel.favoriteSongs.value as ArrayList<Song>
        )
    }

    override fun onOpenMenu(song: Song, position: Int) {
        MenuBottomFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Song, song)
            }
        }.show(requireActivity().supportFragmentManager, null)
    }

}
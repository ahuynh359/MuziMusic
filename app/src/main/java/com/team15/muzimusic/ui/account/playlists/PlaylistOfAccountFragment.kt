package com.team15.muzimusic.ui.account.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.team15.muzimusic.adapter.PlaylistAdapter
import com.team15.muzimusic.adapter.PlaylistClickListener
import com.team15.muzimusic.base.fragments.BaseDialogFragment
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.data.models.Playlist
import com.team15.muzimusic.databinding.FragmentPlaylistOfAccountBinding
import com.team15.muzimusic.ui.account.AccountViewModel
import com.team15.muzimusic.ui.account.playlist_detail.PlaylistDetailFragment

class PlaylistOfAccountFragment : BaseDialogFragment(), PlaylistClickListener {

    private lateinit var binding: FragmentPlaylistOfAccountBinding
    private val viewModel by viewModels<AccountViewModel>({ requireActivity() })

    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistOfAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playlistAdapter = PlaylistAdapter(mutableListOf(), this)

        binding.recyclerPlaylist.apply {
            adapter = playlistAdapter
            layoutManager = LinearLayoutManager(this@PlaylistOfAccountFragment.context)
        }

        viewModel.playlists.observe(this) {
            playlistAdapter.setData(it)
        }
    }

    override fun onPlaylistClick(playlist: Playlist) {
        val fragment = PlaylistDetailFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(Constants.Playlist, playlist)
        }
        fragment.show(requireActivity().supportFragmentManager, null)
    }

    override fun onPlaylistMoreMenuClick(playlist: Playlist, position: Int) {
    }
}
package com.team15.muzimusic.ui.account.album_detail

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
import com.team15.muzimusic.data.models.Album
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.databinding.FragmentAlbumDetailBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.menubottom.MenuBottomFragment
import com.team15.muzimusic.ui.player.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : BaseDialogFragment(), SongClickListener {

    private lateinit var binding: FragmentAlbumDetailBinding
    private val viewModel by viewModels<AlbumDetailViewModel>()

    private lateinit var songAdapter: SongLiteAdapter

    private lateinit var album: Album
    private var needReload: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val album: Album? = arguments?.getParcelable(Constants.Album)
        needReload = arguments?.getBoolean(Constants.NeedReload) ?: false

        if (album == null) dismiss()
        else this.album = album

        if (needReload) {
            viewModel.getSongsOfAlbum(this.album.idAlbum)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)

        if (needReload) {
            binding.pbLoading.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongLiteAdapter(this)
        setInfoPlaylist()

        binding.recyclerSong.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(this@AlbumDetailFragment.context)
        }

        viewModel.songs.observe(this) {
            binding.pbLoading.visibility = View.GONE
            album.songs = it
            setInfoPlaylist()
        }
    }

    private fun setInfoPlaylist() {
        songAdapter.differ.submitList(album.songs)

        binding.tvAlbumName.text = album.name
        binding.tvAccountName.text = album.account?.accountName
        album.songs?.let {
            binding.tvTotalSongs.text = "${it.size}"
        }
    }

    override fun onSongClick(song: Song) {
        startActivity(Intent(context, PlayerActivity::class.java))
        Helper.sendMusicAction(
            requireContext(),
            MusicService.ACTION_PLAY,
            song,
            album.songs as ArrayList<Song>
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
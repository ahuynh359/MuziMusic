package com.team15.muzimusic.ui.home.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.team15.muzimusic.adapter.*
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.data.models.Playlist
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.Type
import com.team15.muzimusic.databinding.FragmentDiscoverBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.account.AccountActivity
import com.team15.muzimusic.ui.account.playlist_detail.PlaylistDetailFragment
import com.team15.muzimusic.ui.player.PlayerActivity
import com.team15.muzimusic.ui.type.TypeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : Fragment(), PlaylistClickListener, TypeClickListener,
    AccountClickListener {

    private lateinit var binding: FragmentDiscoverBinding
    private val viewModel by viewModels<DiscoverViewModel>()

    private lateinit var typeAdapter: TypeAdapter
    private lateinit var newSongAdapter: SongSmallAdapter
    private lateinit var followSongAdapter: SongSmallAdapter
    private lateinit var topSongAdapter: SongTopAdapter
    private lateinit var playlistAdapter: PlaylistSmallAdapter
    private lateinit var singerAdapter: SingerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupType()
        setupNewestSong()
        setupFollowSong()
        setupTopSong()
        setupPlaylist()
        setupSingers()

        binding.swipeRefresh.setOnRefreshListener {
            fetchData()
        }
    }

    private fun fetchData() {
        viewModel.getTypes()
        viewModel.getNewestSongs(1)
        viewModel.getFollowSongs(1)
        viewModel.getBestSongs()
        viewModel.getTopPlaylists()
        viewModel.getTopAccounts()
    }

    override fun onStart() {
        super.onStart()

        fetchData()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerNewSong.startShimmer()
        binding.shimmerFollowSong.startShimmer()
        binding.shimmerPlaylist.startShimmer()
        binding.shimmerTopSong.startShimmer()
        binding.shimmerTopSinger.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerNewSong.stopShimmer()
        binding.shimmerFollowSong.stopShimmer()
        binding.shimmerPlaylist.stopShimmer()
        binding.shimmerTopSong.stopShimmer()
        binding.shimmerTopSinger.stopShimmer()
    }

    private fun setupType() {
        typeAdapter = TypeAdapter(this)
        viewModel.types.observe(viewLifecycleOwner) {
            typeAdapter.differ.submitList(it)
        }

        binding.recyclerTypes.apply {
            adapter = typeAdapter
            layoutManager = LinearLayoutManager(
                this@DiscoverFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        setHorizontalRecyclerScroll(binding.recyclerTypes)
    }

    private fun setupNewestSong() {
        newSongAdapter = SongSmallAdapter(object:SongClickListener{
            override fun onSongClick(song: Song) {
                viewModel.newestSongs.value?.let {
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
            }
        })

        viewModel.newestSongs.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false

            binding.shimmerNewSong.stopShimmer()
            binding.shimmerNewSong.visibility = View.GONE
            binding.recyclerNewSong.visibility = View.VISIBLE
            newSongAdapter.differ.submitList(it)
        }
        binding.recyclerNewSong.apply {
            adapter = newSongAdapter
            layoutManager =
                LinearLayoutManager(
                    this@DiscoverFragment.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }

        setHorizontalRecyclerScroll(binding.recyclerNewSong)
    }

    private fun setupFollowSong() {
        followSongAdapter = SongSmallAdapter(object:SongClickListener{
            override fun onSongClick(song: Song) {
                viewModel.followSongs.value?.let {
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
            }
        })
        viewModel.followSongs.observe(viewLifecycleOwner) {
            binding.shimmerFollowSong.stopShimmer()
            binding.shimmerFollowSong.visibility = View.GONE
            binding.recyclerFollowSong.visibility = View.VISIBLE
            followSongAdapter.differ.submitList(it)
        }

        binding.recyclerFollowSong.apply {
            adapter = followSongAdapter
            layoutManager =
                LinearLayoutManager(
                    this@DiscoverFragment.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }

        setHorizontalRecyclerScroll(binding.recyclerFollowSong)
    }

    private fun setupTopSong() {
        topSongAdapter = SongTopAdapter(object:SongClickListener{
            override fun onSongClick(song: Song) {
                viewModel.bestSongs.value?.let {
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
            }
        })
        viewModel.bestSongs.observe(viewLifecycleOwner) {
            binding.shimmerTopSong.stopShimmer()
            binding.shimmerTopSong.visibility = View.GONE
            binding.recyclerTopSong.visibility = View.VISIBLE
            topSongAdapter.differ.submitList(it)
        }

        binding.recyclerTopSong.apply {
            adapter = topSongAdapter
            layoutManager =
                LinearLayoutManager(
                    this@DiscoverFragment.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }

        setHorizontalRecyclerScroll(binding.recyclerTopSong)
    }

    private fun setupPlaylist() {
        playlistAdapter = PlaylistSmallAdapter(this)
        viewModel.topPlaylists.observe(viewLifecycleOwner) {
            binding.shimmerPlaylist.stopShimmer()
            binding.shimmerPlaylist.visibility = View.GONE
            binding.recyclerPlaylist.visibility = View.VISIBLE
            playlistAdapter.differ.submitList(it)
            for(i in it){
                Log.d("vinhabc", "${i.name} - ${i.songs?.size}")
            }
        }

        binding.recyclerPlaylist.apply {
            adapter = playlistAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        setHorizontalRecyclerScroll(binding.recyclerPlaylist)
    }

    private fun setupSingers() {
        singerAdapter = SingerAdapter(this)
        viewModel.topAccounts.observe(viewLifecycleOwner) {
            binding.shimmerTopSinger.stopShimmer()
            binding.shimmerTopSinger.visibility = View.GONE
            binding.recyclerTopSinger.visibility = View.VISIBLE
            singerAdapter.differ.submitList(it)
        }

        binding.recyclerTopSinger.apply {
            adapter = singerAdapter
            layoutManager =
                LinearLayoutManager(
                    this@DiscoverFragment.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }

        setHorizontalRecyclerScroll(binding.recyclerTopSinger)
    }


    private fun setHorizontalRecyclerScroll(recyclerView: RecyclerView) {
        recyclerView.addOnItemTouchListener(object : OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
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

    override fun onAccountClick(account: Account) {
        startActivity(Intent(context, AccountActivity::class.java).apply {
            putExtra(Constants.Account, account)
        })
    }

    override fun onTypeClick(type: Type) {
        startActivity(Intent(context, TypeActivity::class.java).apply {
            putExtra(Constants.Type, type)
        })
    }

}
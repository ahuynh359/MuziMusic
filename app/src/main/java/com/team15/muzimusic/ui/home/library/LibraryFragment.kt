package com.team15.muzimusic.ui.home.library

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.team15.muzimusic.R
import com.team15.muzimusic.adapter.MenuIndividualAdapter
import com.team15.muzimusic.common.DataLocal
import com.team15.muzimusic.data.models.MenuClickListener
import com.team15.muzimusic.data.models.MenuIndividual
import com.team15.muzimusic.data.models.MenuIndividualType.*
import com.team15.muzimusic.databinding.FragmentIndividualBinding
import com.team15.muzimusic.ui.admin.account.LockAccountActivity
import com.team15.muzimusic.ui.admin.type.ManagerTypeActivity
import com.team15.muzimusic.ui.home.library.album.AlbumFragment
import com.team15.muzimusic.ui.home.library.favoritesongs.FavoriteSongsFragment
import com.team15.muzimusic.ui.home.library.followers.FollowersFragment
import com.team15.muzimusic.ui.home.library.followings.FollowingsFragment
import com.team15.muzimusic.ui.home.library.mysongs.MySongsFragment
import com.team15.muzimusic.ui.home.library.playlist.PlaylistFragment

class LibraryFragment : Fragment(), MenuClickListener {

    private lateinit var binding: FragmentIndividualBinding

    private lateinit var menuAdapter: MenuIndividualAdapter


    private val menus = arrayListOf(
        MenuIndividual("My Songs", R.drawable.ic_headphone, SONG),
        MenuIndividual("Love", R.drawable.ic_love_white, FAVORITE),
        MenuIndividual("Follow Me", R.drawable.ic_follower, FOLLOWER),
        MenuIndividual("Following", R.drawable.ic_following, FOLLOWING),
        MenuIndividual("Playlist", R.drawable.ic_playlist, PLAYLIST),
        MenuIndividual("Album", R.drawable.ic_album, ALBUM),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (DataLocal.myAccount.role == 1) {
            menus.add(MenuIndividual("Manage Type", R.drawable.ic_playlist, MANAGE_TYPE))
            menus.add(MenuIndividual("Manage Lock Account", R.drawable.ic_playlist, MANAGE_LOCK_ACCOUNT))
            menus.add(MenuIndividual("Statistic", R.drawable.ic_playlist, STATISTIC))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIndividualBinding.inflate(layoutInflater, container, false)

        menuAdapter = MenuIndividualAdapter(this)
        menuAdapter.differ.submitList(menus)

        binding.recyclerMenu.apply {
            adapter = menuAdapter
            layoutManager = GridLayoutManager(
                this@LibraryFragment.context,
                2,
                GridLayoutManager.VERTICAL,
                false
            )
        }


        return binding.root
    }


    override fun onMenuClick(menu: MenuIndividual) {
        when (menu.type) {
            SONG -> showFragment(MySongsFragment())
            FAVORITE -> showFragment(FavoriteSongsFragment())
            FOLLOWER -> showFragment(FollowersFragment())
            FOLLOWING -> showFragment(FollowingsFragment())
            PLAYLIST -> showFragment(PlaylistFragment())
            ALBUM -> showFragment(AlbumFragment())
            MANAGE_TYPE -> {
                startActivity(Intent(requireContext(), ManagerTypeActivity::class.java))
            }
            else -> {
                startActivity(Intent(requireContext(), LockAccountActivity::class.java))
            }

        }
    }

    private fun showFragment(fragment: DialogFragment) {
        fragment.show(requireActivity().supportFragmentManager, null)
    }

}
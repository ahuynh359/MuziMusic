package com.team15.muzimusic.ui.menubottom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.adapter.MenuBottomAdapter
import com.team15.muzimusic.base.fragments.BaseDialogFragment
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.DataLocal
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.MenuBottom
import com.team15.muzimusic.data.models.MenuBottomClickListener
import com.team15.muzimusic.data.models.MenuBottomType.*
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.databinding.FragmentMenuBottomBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.account.album_detail.AlbumDetailFragment
import com.team15.muzimusic.ui.formsong.FormSongActivity
import com.team15.muzimusic.ui.menubottom.playlists.SongToPlaylistFragment
import com.team15.muzimusic.ui.menubottom.singers.SingersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuBottomFragment : BaseDialogFragment(), MenuBottomClickListener {

    private lateinit var binding: FragmentMenuBottomBinding
    private val viewModel by viewModels<MenuBottomViewModel>({ requireActivity() })
    private lateinit var menuAdapter: MenuBottomAdapter

    private lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val song: Song? = arguments?.getParcelable(Constants.Song)
        if (song == null) dismiss()
        else this.song = song

        viewModel.position = arguments?.getInt(Constants.Position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuAdapter = MenuBottomAdapter(this)
        menuAdapter.differ.submitList(generateMenu())

        binding.recyclerMenu.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(this@MenuBottomFragment.context)
        }

        if (song.image.isNotEmpty()) Picasso.get().load(song.image).placeholder(R.drawable.songs)
            .fit()
            .into(binding.imgSongAvatar)

        binding.tvSongName.text = song.name
        binding.tvAccountName.text = song.account.accountName ?: ""

    }

    private fun generateMenu(): ArrayList<MenuBottom> {
        val items = arrayListOf<MenuBottom>()

        val myAccount = DataLocal.myAccount

        if (myAccount.idAccount == song.account.idAccount) {
            items.add(MenuBottom("Edit Song", R.drawable.ic_edit, EDIT))
        }

        if (song.loveStatus)
            items.add(
                MenuBottom(
                    "Unlove",
                    R.drawable.ic_hearted,
                    REMOVE_FAVORITE
                )
            )
        else
            items.add(
                MenuBottom(
                    "Love",
                    R.drawable.ic_heart,
                    FAVORITE
                )
            )

        items.addAll(coreMenu)

        return items
    }

    private var coreMenu = arrayListOf(
        MenuBottom("Add to playlist", R.drawable.ic_playlist, PLAYLIST),
        MenuBottom("Album", R.drawable.ic_album, ALBUM),
        MenuBottom("Singer", R.drawable.ic_user, SINGERS),

        )

    override fun onMenuClick(menu: MenuBottom) {
        when (menu.type) {
            EDIT -> {
                startActivity(Intent(context, FormSongActivity::class.java).apply {
                    putExtra(Constants.Song, song)
                })
            }

            FAVORITE -> {
                viewModel.loveSong(song)
            }

            REMOVE_FAVORITE -> {
                viewModel.unLoveSong(song)
            }

            ALBUM -> {
                if (song.album != null) {
                    AlbumDetailFragment().apply {
                        arguments = Bundle().apply {
                            putParcelable(Constants.Album, song.album)
                            putBoolean(Constants.NeedReload, true)
                        }
                    }.show(requireActivity().supportFragmentManager, null)
                } else {
                    Toast.makeText(context, "Bài hát nào không thuộc album nào", Toast.LENGTH_SHORT)
                        .show()
                }

            }

            SINGERS -> {
                SingersFragment().apply {
                    arguments = Bundle().apply {
                        Log.d("vinh", "singers size: ${song?.singers?.size}")
                        putParcelableArrayList(
                            Constants.Singers,
                            song?.singers as ArrayList
                        )
                    }
                }.show(requireActivity().supportFragmentManager, null)
            }

            PLAYLIST -> {
                SongToPlaylistFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(Constants.Song, song)
                    }
                }.show(requireActivity().supportFragmentManager, null)
            }

            HEAD_OF_PLAYLIST -> {
                Helper.sendMusicAction(
                    requireContext(),
                    MusicService.ACTION_ADD_SONG_NEXT,
                    song
                )
                Toast.makeText(context, "Đã thêm vào danh sách phát", Toast.LENGTH_SHORT).show()
            }

            TAIL_OF_PLAYLIST -> {
                Helper.sendMusicAction(
                    requireContext(),
                    MusicService.ACTION_ADD_SONG_TAIL,
                    song
                )
                Toast.makeText(context, "Đã thêm vào cuối danh sách phát", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {}
        }

        dismiss()
    }

}
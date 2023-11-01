package com.team15.muzimusic.ui.type

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team15.muzimusic.adapter.SongClickListener
import com.team15.muzimusic.adapter.SongLiteAdapter
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.Type
import com.team15.muzimusic.databinding.ActivityTypeBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.menubottom.MenuBottomFragment
import com.team15.muzimusic.ui.player.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TypeActivity : AppCompatActivity(), SongClickListener {

    private lateinit var binding: ActivityTypeBinding
    private val viewModel by viewModels<TypeViewModel>()

    private lateinit var type: Type

    private lateinit var songAdapter: SongLiteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        getData()
        setUpRecyclerView()

        viewModel.isLoading.observe(this) {
            if (it)
                binding.pbLoading.visibility = View.VISIBLE
            else
                binding.pbLoading.visibility = View.GONE

        }

        viewModel.songsOfType.observe(this) {
            songAdapter.differ.submitList(it)

            if (it.isEmpty()) {
                Toast.makeText(this, "No song belongs to this type", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun getData() {
        if (!intent.hasExtra(Constants.Type)) finish()
        type = intent?.getParcelableExtra(Constants.Type)!!

        viewModel.getSongsOfType(type)
        binding.toolbar.title = type.name
    }

    private fun setUpRecyclerView() {
        songAdapter = SongLiteAdapter(this)

        binding.recyclerSong.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(this@TypeActivity)
        }

        binding.recyclerSong.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.isLoading.value?.let {
                        if (!it) viewModel.getSongsOfType(type)
                    }
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSongClick(song: Song) {
        startActivity(Intent(this, PlayerActivity::class.java))
        Helper.sendMusicAction(
            this,
            MusicService.ACTION_PLAY,
            song,
            viewModel.songsOfType.value as ArrayList<Song>
        )
    }

    override fun onOpenMenu(song: Song, position: Int) {
        MenuBottomFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Song, song)
            }
        }.show(supportFragmentManager, null)
    }

}
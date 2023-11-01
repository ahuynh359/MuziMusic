package com.team15.muzimusic.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.adapter.EventBusModel
import com.team15.muzimusic.adapter.ViewPagerAdapter
import com.team15.muzimusic.base.activities.BaseActivity
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.DataLocal
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.singersToString
import com.team15.muzimusic.databinding.ActivityHomeBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.account.AccountActivity
import com.team15.muzimusic.ui.home.home.DiscoverFragment
import com.team15.muzimusic.ui.home.library.LibraryFragment
import com.team15.muzimusic.ui.notification.NotificationActivity
import com.team15.muzimusic.ui.player.PlayerActivity
import com.team15.muzimusic.ui.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.team15.muzimusic.ui.home.chart.ChartFragment

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)

        if (!isOnline()) {
            showErrorDialog("No Internet Connected")
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupBottomNavigation()

        if (DataLocal.myAccount.avatar.isNotEmpty()) {
            Picasso.get().load(DataLocal.myAccount.avatar).fit().into(binding.toolbar.imvAvatar)
        }

        binding.toolbar.tvHi.text = Helper.greetBasedOnTime()
        clickEvents()
        observers()

        deviceToken()
    }


    private fun deviceToken() {
        /*FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("vinh", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            Log.d("vinhtoken", token)
            viewModel.sendAccountDevice(token)
        })*/
    }

    private fun observers() {
        viewModel.song.observe(this) { song ->
            if (song == null) {
                binding.playerMini.visibility = View.INVISIBLE
            } else {
                binding.playerMini.visibility = View.VISIBLE
                binding.tvSongName.text = song.name
                binding.tvSingerName.text = song.singersToString()
                Picasso.get().load(song.image).placeholder(R.drawable.songs)
                    .into(binding.imgSongAvatar)
            }
        }

        viewModel.isPlaying.observe(this) {
            binding.btnPlayPause.setImageResource(
                if (it) R.drawable.ic_pause
                else R.drawable.ic_play
            )
        }

        viewModel.numUnreadNotification.observe(this) {
            binding.toolbar.tvCount.apply {
                if (it == 0) visibility = View.INVISIBLE
                else {
                    visibility = View.VISIBLE
                    text = "$it"
                }
            }
        }
    }

    private fun clickEvents() {
        binding.playerMini.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }

        binding.toolbar.imvAvatar.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java).apply {
                putExtra(Constants.Account, DataLocal.myAccount)
            })
        }

        binding.toolbar.imvNoti.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }



        binding.btnPlayPause.setOnClickListener {
            Log.d("vinh", "action_play")
            sendMusicAction(MusicService.ACTION_PLAY)
        }

        binding.btnNext.setOnClickListener {
            sendMusicAction(MusicService.ACTION_NEXT)
        }

        binding.btnClearMusic.setOnClickListener {
            Helper.sendMusicAction(this, MusicService.ACTION_CLEAR)
        }

        binding.toolbar.btnSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }


    private fun sendMusicAction(
        action: Int,
        song: Song? = null,
        songList: ArrayList<Song> = arrayListOf()
    ) {
        val intent = Intent(applicationContext, MusicService::class.java)

        intent.putExtra("action", action)
        song?.let {
            val bundle = Bundle().apply {
                putParcelable(Constants.Song, it)
                putParcelableArrayList(Constants.SongList, songList)
            }
            intent.putExtra(Constants.Data, bundle)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    fun onSongInfo(event: EventBusModel.SongInfoEvent) {
        Log.d("vinh", "home recieve song: ${event.song?.name}")
        viewModel.song.postValue(event.song)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    fun onMusicPlaying(event: EventBusModel.MusicPlayingEvent) {
        viewModel.isPlaying.postValue(event.isPlaying)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    fun onClearMusic(event: EventBusModel.ClearMusic) {
        viewModel.song.postValue(null)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onMusicTime(timeEvent: EventBusModel.MusicTimeEvent) {
    }

    override fun onResume() {
        super.onResume()
        binding.btnPlayPause.setImageResource(
            if (viewModel.isPlaying.value!!) R.drawable.ic_pause
            else R.drawable.ic_play
        )
        if (DataLocal.myAccount.avatar.isNotEmpty()) {
            Picasso.get().load(DataLocal.myAccount.avatar).placeholder(R.drawable.ic_user)
                .fit().into(binding.toolbar.imvAvatar)
        }

        EventBus.getDefault().post(EventBusModel.RequestSongEvent())
    }

    private fun setupViewPager() {
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val fragmentsList: ArrayList<Fragment> =
            arrayListOf(
                DiscoverFragment(),

                LibraryFragment(),
                ChartFragment(),
            )
        binding.viewPager.adapter = ViewPagerAdapter(fragmentsList, this)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNav.selectedItemId = items[position]
            }
        })
    }

    var items =
        arrayListOf(R.id.navigation_home, R.id.navigation_library, R.id.navigation_chart)

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            for (i in 0 until items.size) {
                if (items[i] == it.itemId) {
                    binding.viewPager.currentItem = i
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
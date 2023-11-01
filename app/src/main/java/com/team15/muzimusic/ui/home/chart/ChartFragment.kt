package com.team15.muzimusic.ui.home.chart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.makeramen.roundedimageview.RoundedImageView
import com.team15.muzimusic.R
import com.team15.muzimusic.adapter.SongAdapter
import com.team15.muzimusic.adapter.SongClickListener
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.ListenOfDay
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.databinding.FragmentChartBinding
import com.team15.muzimusic.service.MusicService
import com.team15.muzimusic.ui.home.white.ChartViewModel
import com.team15.muzimusic.ui.menubottom.MenuBottomFragment
import com.team15.muzimusic.ui.menubottom.MenuBottomViewModel
import com.team15.muzimusic.ui.player.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Integer.min
import java.util.*
import kotlin.math.abs


@AndroidEntryPoint
class ChartFragment : Fragment(), SongClickListener {

    private lateinit var binding: FragmentChartBinding
    private val viewModel by viewModels<ChartViewModel>()
    private val menuViewModel by viewModels<MenuBottomViewModel>({ requireActivity() })

    private lateinit var songAdapter: SongAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerTopSong.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerTopSong.stopShimmer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChartBinding.inflate(layoutInflater, container, false);

        menuViewModel.actionLoveStatus.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    menuViewModel.position?.let { position ->
                        val list = songAdapter.getSongs()
                        list[position].loveStatus = !list[position].loveStatus
                        songAdapter.setData(list)
                    }
                }
                Toast.makeText(context, menuViewModel.message, Toast.LENGTH_SHORT).show()
                menuViewModel.actionLoveStatus.value = null
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchData()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongAdapter(this)
        binding.recyclerSong.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.topSongsChart.observe(viewLifecycleOwner) {
            // Chỉ lấy top 3
            values.clear()
            for (topIndex in 0 until min(3, it.size)) {
                values.add(ArrayList())

                // Lấy 10 ngày gần nhất
                // Duyệt từ 10 ngày trước, cho đến hiện tại
                it[topIndex].listenDetail?.let { listListen ->
                    val calendar = Calendar.getInstance()
                    calendar.add(Calendar.DAY_OF_MONTH, -10)
                    for (cnt in 0 until 10) {
                        calendar.add(Calendar.DAY_OF_MONTH, 1)
                        val numListen = getListenOfDay(calendar.time, listListen)
                        values[topIndex].add(Entry(cnt.toFloat(), numListen.toFloat()))
                    }
                }
            }

            setupChart()
        }

        viewModel.topSongs.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false

            binding.shimmerTopSong.stopShimmer()
            binding.shimmerTopSong.visibility = View.GONE
            binding.recyclerSong.visibility = View.VISIBLE
            songAdapter.setData(it)

            viewModel.getTopSongDrawable(requireContext())
        }

        viewModel.songDrawables.observe(viewLifecycleOwner) {
            binding.chart.data = generateDataLine()
        }
    }

    private fun getListenOfDay(checkDate: Date, listens: List<ListenOfDay>): Int {
        for (listenOfDay in listens) {
            val date = Helper.stringToDate(listenOfDay.day)
            val calendar1 = Calendar.getInstance()
            val calendar2 = Calendar.getInstance()

            calendar1.time = checkDate
            date?.let {
                calendar2.time = it
                if (calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
                    && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                ) return listenOfDay.listen
            }
        }
        return 0
    }

    private fun setupChart() {
        binding.chart.apply {
            description.isEnabled = false
            setDrawGridBackground(false)
            isDragEnabled = false
            setScaleEnabled(false)
            setPinchZoom(false)
            legend.isEnabled = false

            xAxis.apply {
                labelCount = 9
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                textColor = ContextCompat.getColor(context, R.color.white)
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val calendar = Calendar.getInstance()
                        calendar.add(Calendar.DAY_OF_MONTH, value.toInt() - 9)
                        return "${calendar.get(Calendar.DAY_OF_MONTH)}"
                    }
                }
            }
            axisLeft.apply {
                isEnabled = false
            }
            axisRight.apply {
                labelCount = 4
                setDrawGridLines(false)
                textColor = ContextCompat.getColor(context, R.color.white)
            }

//            animateX(1500)
            animateY(1500)
            data = generateDataLine()

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    binding.chart.data = generateDataLine(h?.dataSetIndex ?: -1)
                }

                override fun onNothingSelected() {

                }
            })
        }
    }

    private val values: ArrayList<ArrayList<Entry>> = ArrayList()

    private fun generateDataLine(whiteSetIndex: Int = -1): LineData {

        val sets: ArrayList<ILineDataSet> = ArrayList()
        val dataSets: ArrayList<LineDataSet> = ArrayList()

        for (i in 0 until values.size) {
            var lineDataSet: LineDataSet
            if (whiteSetIndex == i) {
                val whiteValue = values[i].toMutableList()
                val randomIndex = abs(Random().nextInt() % 9 + 1)

                val a = RoundedImageView(context)
                a.setImageDrawable(viewModel.songDrawables.value?.get(i))
                a.cornerRadius = 24f
                a.borderWidth = Helper.dpToPixel(2f, requireContext()).toFloat()
                a.borderColor = Constants.colorsTopSong[i]

                whiteValue[randomIndex] = Entry(
                    randomIndex.toFloat(),
                    whiteValue[randomIndex].y,
                    a.drawable
                )

                lineDataSet = LineDataSet(whiteValue, "Top ${i + 1}").apply {
                    setDrawCircles(true)
                    setDrawCircleHole(true)
                    circleRadius = 3.5f
                    setCircleColor(Constants.colorsTopSong[i])
                }
            } else {
                lineDataSet = LineDataSet(values[i], "Top ${i + 1}").apply {
                    setDrawCircles(false)
                    setDrawCircleHole(false)
                }
            }

            lineDataSet.apply {
                lineWidth = 1.5f
                color = Constants.colorsTopSong[i]
                setDrawValues(false)
            }
            dataSets.add(lineDataSet)
            sets.add(dataSets[i])
        }

        return LineData(sets)
    }

    override fun onSongClick(song: Song) {
        startActivity(Intent(context, PlayerActivity::class.java))

        if (song.songFile == null) {
            viewModel.saveSong(song)
        }

        Helper.sendMusicAction(
            requireContext(),
            MusicService.ACTION_PLAY,
            song,
            viewModel.topSongs.value as ArrayList<Song>
        )
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
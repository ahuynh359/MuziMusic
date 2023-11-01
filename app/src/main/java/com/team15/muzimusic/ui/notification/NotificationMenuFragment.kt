package com.team15.muzimusic.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.team15.muzimusic.base.fragments.BaseDialogFragment
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.data.models.Notification
import com.team15.muzimusic.databinding.FragmentNotificationMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationMenuFragment : BaseDialogFragment() {

    private lateinit var binding: FragmentNotificationMenuBinding
    private val viewModel by viewModels<NotificationViewModel>({ requireActivity() })

    private var notification: Notification? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notification = arguments?.getParcelable(Constants.Notification)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationMenuBinding.inflate(inflater, container, false)

        binding.menuReadNotification.setOnClickListener {
            notification?.let {
                viewModel.readNotification(it)
                this.dismiss()
            }
        }

        binding.menuDeleteNotification.setOnClickListener {
            notification?.let {
                viewModel.deleteNotification(it)
                this.dismiss()
            }
        }

        return binding.root
    }

}
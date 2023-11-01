package com.team15.muzimusic.data.services.notification

import com.team15.muzimusic.base.network.BaseRemoteService
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.apis.NotificationAPI
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.models.Notification
import com.team15.muzimusic.data.models.NotificationCountJson
import com.team15.muzimusic.data.models.NotificationJson
import javax.inject.Inject

class NotificationRemoteService @Inject constructor(
    private val notificationAPI: NotificationAPI
) : BaseRemoteService() {

    suspend fun getAllNotifications(): List<NotificationJson>? {
        val response = callApi { notificationAPI.getAllNotifications() }
        return if (response is NetworkResult.Success)
            response.body.data
        else null

    }

    suspend fun readNotification(notification: Notification): NetworkResult<MessageJson> {
        return callApi { notificationAPI.readNotification(notification.idNotification) }
    }

    suspend fun deleteNotification(notification: Notification): NetworkResult<MessageJson> {
        return callApi { notificationAPI.deleteNotification(notification.idNotification) }
    }

    suspend fun readAllNotification(): NetworkResult<MessageJson> {
        return callApi { notificationAPI.readAllNotification() }
    }

    suspend fun deleteAllNotification(): NetworkResult<MessageJson> {
        return callApi { notificationAPI.deleteAllNotification() }
    }

    suspend fun countUnreadNotification(): NetworkResult<NotificationCountJson> {
        return callApi { notificationAPI.countUnreadNotification() }
    }
}
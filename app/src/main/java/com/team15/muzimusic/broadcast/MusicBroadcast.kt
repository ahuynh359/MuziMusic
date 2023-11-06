package com.team15.muzimusic.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.team15.muzimusic.service.MusicService

class MusicBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val actionMusic = intent.getIntExtra(MusicService.INTENT_ACTION, -1)
        val intentService = Intent(context, MusicService::class.java)
        intentService.putExtra("action", actionMusic)
        context.startService(intentService)
    }
}
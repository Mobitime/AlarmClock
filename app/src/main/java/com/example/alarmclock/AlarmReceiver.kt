package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class AlarmReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val activityIntent = Intent(context, AlarmActivity::class.java)
        activityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(activityIntent)
    }
    }


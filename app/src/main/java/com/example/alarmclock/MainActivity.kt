package com.example.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private var calendar: Calendar? = null
    private var materialTimePicker: MaterialTimePicker? = null
    private lateinit var alarmButtonBTN: Button
    private lateinit var timeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Будильник"

        timeTextView = findViewById(R.id.timeTextView)
        alarmButtonBTN = findViewById(R.id.alarmButtonBTN)

        alarmButtonBTN.setOnClickListener {
            materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время будильника")
                .build()
            materialTimePicker!!.addOnPositiveButtonClickListener {
                calendar = Calendar.getInstance()
                calendar?.set(Calendar.SECOND, 0)
                calendar?.set(Calendar.MILLISECOND, 0)
                calendar?.set(Calendar.MINUTE, materialTimePicker!!.minute)
                calendar?.set(Calendar.HOUR_OF_DAY, materialTimePicker!!.hour)

                val formattedTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar!!.time)
                timeTextView.text = "Установлено: $formattedTime"

                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar!!.timeInMillis,
                    getAlarmPendingIntent()
                )
                Toast.makeText(this, "Будильник установлен на $formattedTime", Toast.LENGTH_LONG).show()
            }
            materialTimePicker!!.show(supportFragmentManager, "tag_picker")
        }
    }

    private fun getAlarmPendingIntent(): PendingIntent {
        val intent = Intent(this, AlarmReceiver::class.java)
        return PendingIntent.getBroadcast(
            this,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                finishAffinity()
                exitProcess(0)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

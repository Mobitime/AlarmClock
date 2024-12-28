package com.example.alarmclock

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class AlarmActivity : AppCompatActivity() {

private lateinit var StopAlarmButtonBTN: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Будильник"

        StopAlarmButtonBTN = findViewById(R.id.StopAlarmButtonBTN)
        StopAlarmButtonBTN.setOnClickListener{
            finish()
            exitProcess(0)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                finishAffinity()
                exitProcess(0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
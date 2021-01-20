package com.example.myapplication



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_alarms.*
import kotlinx.android.synthetic.main.one_alarm.view.*
import java.util.*



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun newAlarmManager(view: View){
        val intent=Intent(this@MainActivity,ListOfAlarms::class.java)
        intent.putExtra("game","lack" )
        startActivity(intent)
    }
    fun newGameTest(view: View){
        val intent=Intent(this@MainActivity,GameTest::class.java)
        startActivity(intent)
    }
    fun newSettings(view: View){
        val intent=Intent(this@MainActivity,Settings::class.java)
        startActivity(intent)
    }


}

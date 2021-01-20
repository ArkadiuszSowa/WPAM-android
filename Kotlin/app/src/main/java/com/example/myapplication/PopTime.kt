package com.example.myapplication


import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R


class PopTime(): DialogFragment(){
    var tp1:TimePicker?=null
    var Id_local:Int?=null
    var saveData:SaveData?=null
    constructor(Id:Int) : this() {
        this.Id_local=Id
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var myView= inflater!!.inflate(R.layout.pop_time,container,false)
        var buDone=myView.findViewById(R.id.buDone) as Button
        tp1=myView.findViewById(R.id.tp1) as TimePicker
        var ListOfAl= activity as ListOfAlarms
        saveData=SaveData(ListOfAl.applicationContext)

        buDone.setOnClickListener {
            //val ma=activity as MainActivity
            ListOfAl= activity as ListOfAlarms

            if(Build.VERSION.SDK_INT>=23) {
               //ma.SetTime(tp1.hour, tp1.minute,Id_local!!)
                if(check_value_not_existing(tp1!!.hour, tp1!!.minute,Id_local!!)){
                    Toast.makeText(ListOfAl.applicationContext,"Alarm on this time already existing",Toast.LENGTH_LONG).show()
                }else {
                    ListOfAl.SetTime(tp1!!.hour, tp1!!.minute, Id_local!!)
                    Log.d("tag", "Id_local się udało,opcja version>=23, fun SetTime")
                }
            }else{
                if(check_value_not_existing(tp1!!.currentHour, tp1!!.currentMinute,Id_local!!)){
                    Toast.makeText(ListOfAl.applicationContext,"Alarm on this time already existing",Toast.LENGTH_LONG).show()
                }else {
                    ListOfAl.SetTime(tp1!!.currentHour, tp1!!.currentMinute, Id_local!!)
                    Log.d("tag", "Id_local się udało, opcja else, fun SetTime")
                }
            }

            this.dismiss()
        }


        return myView
    }


    fun check_value_not_existing(hour:Int, minute:Int, Id: Int):Boolean {
        for (i in 0..9) {
            if (i != Id) {
                    if (hour == saveData!!.getHour(i) && minute == saveData!!.getMinute(i)) {
                        return true
                    }
            }
        }
        return false
    }
}
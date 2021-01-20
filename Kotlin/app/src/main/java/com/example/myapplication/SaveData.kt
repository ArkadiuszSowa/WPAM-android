package com.example.myapplication


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*

class SaveData{

    var context:Context?=null
    var sharedRef:SharedPreferences?=null
    var hourId:String?=null
    var minuteId:String?=null
    var gameId:String?=null
    var isONId:String?=null
    //var volume:Int?=null

    constructor(context:Context){
        this.context=context
        //Tworzymy SharedPreferences o nazwie ref
        sharedRef=context.getSharedPreferences("ref",Context.MODE_PRIVATE)
    }

    fun SaveData(hour:Int,minute:Int, Id:Int, game:String,isON:Boolean){
        var editor=sharedRef!!.edit()
        //nadajemy odopiednie Id do każdego zapisanego alarmu
        hourId= "hour"+Id.toString()
        minuteId= "minute"+Id.toString()
        gameId="game"+Id.toString()
        isONId="isON"+Id.toString()
        editor.putInt(hourId,hour)
        editor.putInt(minuteId,minute)
        editor.putString(gameId,game)
        editor.putBoolean(isONId,isON)
        editor.commit()
        setAlarm(Id)
    }

    fun saveVol(vol: Int){
        var editor=sharedRef!!.edit()
        editor.putInt("volume",vol)
        editor.commit()
    }

    fun getVolume():Int{
       return  sharedRef!!.getInt("volume",14)
    }

    fun saveSong(song: String){
        var editor=sharedRef!!.edit()
        editor.putString("song",song)
        editor.commit()
    }

    fun getSong(): String? {
        return sharedRef!!.getString("song", "wiedzmin")
    }

    fun  getHour(Id: Int):Int{
        hourId= "hour"+Id.toString()
        //println(hourId + ": " + sharedRef!!.getInt(hourId,100).toString())
        return sharedRef!!.getInt(hourId,100)       //100 to domyślna wartość, ponieważ takiej wartości nigdy nie osiągnie godzina,
    }                                                        //dlatego mogę sobie filtorowac puste wyniki po 100
    fun  getMinute(Id: Int):Int{
        minuteId= "minute"+Id.toString()
       // println(minuteId + ": " + sharedRef!!.getInt(minuteId,0).toString())
        return sharedRef!!.getInt(minuteId,0)
    }
    fun getGame(Id: Int): String? {
        gameId="game"+Id.toString()
        //println(gameId + ": " + sharedRef!!.getString(gameId,"game1"))
        return sharedRef!!.getString(gameId,"game1")
    }
    fun getIsON(Id: Int): Boolean {
        isONId="isON"+Id.toString()
        //println(isONId + ": " + sharedRef!!.getBoolean(isONId,false))
        return sharedRef!!.getBoolean(isONId,false)
    }


    fun setAlarm(Id: Int){
        val hour:Int=getHour(Id)
        val minute:Int=getMinute(Id)
        val calendar=Calendar.getInstance()
        //calendar.add(Calendar.DAY_OF_YEAR, 1)//nadaje przerwe 1 dzień
        calendar.set(Calendar.HOUR_OF_DAY,hour)
        calendar.set(Calendar.MINUTE,minute)
        calendar.set(Calendar.SECOND,0)
        if(System.currentTimeMillis() > calendar.getTimeInMillis())
        {
            calendar.add(Calendar.DAY_OF_YEAR, 1);   ///to avoid firing the alarm immediately
        }

        println("setAlarm dla: " + Id.toString())
            when (Id) {
                0 -> {
                    val am0 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent0 = Intent(context, myBroadcastReceiver::class.java)
                    intent0.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent0.action = "com.tester.alarmmanager0"
                    }else {
                        intent0.action = "lack"
                    }
                     val pi0 = PendingIntent.getBroadcast(context, 0, intent0, PendingIntent.FLAG_UPDATE_CURRENT)
                     am0.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi0)
                }
                1 -> {
                    val am1 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent1 = Intent(context, myBroadcastReceiver::class.java)
                    intent1.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent1.action = "com.tester.alarmmanager1"
                    }else {
                        intent1.action = "lack"
                    }
                    val pi1 = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
                    am1.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi1)
                }
                2 -> {
                    val am2 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent2 = Intent(context, myBroadcastReceiver::class.java)
                    intent2.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent2.action = "com.tester.alarmmanager2"
                    }else {
                        intent2.action = "lack"
                    }
                    val pi2 = PendingIntent.getBroadcast(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
                    am2.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi2)
                }
                3 -> {
                    val am3 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent3 = Intent(context, myBroadcastReceiver::class.java)
                    intent3.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent3.action = "com.tester.alarmmanager3"
                    }else {
                        intent3.action = "lack"
                    }
                    val pi3 = PendingIntent.getBroadcast(context, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
                    am3.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi3)
                }
                4 -> {
                    val am4 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent4 = Intent(context, myBroadcastReceiver::class.java)
                    intent4.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent4.action = "com.tester.alarmmanager4"
                    }else {
                        intent4.action = "lack"
                    }
                    val pi4 = PendingIntent.getBroadcast(context, 0, intent4, PendingIntent.FLAG_UPDATE_CURRENT)
                    am4.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi4)
                }
                5 -> {
                    val am5 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent5 = Intent(context, myBroadcastReceiver::class.java)
                    intent5.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent5.action = "com.tester.alarmmanager5"
                    }else {
                        intent5.action = "lack"
                    }
                    val pi5 = PendingIntent.getBroadcast(context, 0, intent5, PendingIntent.FLAG_UPDATE_CURRENT)
                    am5.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi5)
                }
                6 -> {
                    val am6 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent6 = Intent(context, myBroadcastReceiver::class.java)
                    intent6.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent6.action = "com.tester.alarmmanager6"
                    }else {
                        intent6.action = "lack"
                    }
                    val pi6 = PendingIntent.getBroadcast(context, 0, intent6, PendingIntent.FLAG_UPDATE_CURRENT)
                    am6.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi6)
                }
                7 -> {
                    val am7 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent7 = Intent(context, myBroadcastReceiver::class.java)
                    intent7.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent7.action = "com.tester.alarmmanager7"
                    }else {
                        intent7.action = "lack"
                    }
                    val pi7 = PendingIntent.getBroadcast(context, 0, intent7, PendingIntent.FLAG_UPDATE_CURRENT)
                    am7.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi7)
                }
                8 -> {
                    val am8 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent8 = Intent(context, myBroadcastReceiver::class.java)
                    intent8.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent8.action = "com.tester.alarmmanager8"
                    }else {
                        intent8.action = "lack"
                    }
                    val pi8 = PendingIntent.getBroadcast(context, 0, intent8, PendingIntent.FLAG_UPDATE_CURRENT)
                    am8.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi8)
                }
                9 -> {
                    val am9 = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    var intent9 = Intent(context, myBroadcastReceiver::class.java)
                    intent9.putExtra("Id", Id.toString())
                    if(getIsON(Id)) {
                        intent9.action = "com.tester.alarmmanager9"
                    }else {
                        intent9.action = "lack"
                    }
                    val pi9 = PendingIntent.getBroadcast(context, 0, intent9, PendingIntent.FLAG_UPDATE_CURRENT)
                    am9.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pi9)
                }

            }
    }
}
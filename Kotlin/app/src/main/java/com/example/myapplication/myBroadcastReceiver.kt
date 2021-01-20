package com.example.myapplication


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity


class myBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var b:Bundle?=null
        var newIntent:Intent?=null
            if (intent!!.action.equals("com.tester.alarmmanager0")) {// && saveData!!.getIsON(0)){
                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 0)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(0): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager1")) {// && saveData!!.getIsON(1)) {
                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 1)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(1): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager2")) {// && saveData!!.getIsON(2)) {
                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 2)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(2): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager3")) {// && saveData!!.getIsON(3)) {
                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id",3 )
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(3): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager4")) {// && saveData!!.getIsON(4)) {
                                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 4)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(4): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager5")) {// && saveData!!.getIsON(5)) {
                                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 5)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(5): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager6")) {// && saveData!!.getIsON(6)) {
                                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 6)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(6): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager7")) {// && saveData!!.getIsON(7)){
                                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 7)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(7): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager8")) {// && saveData!!.getIsON(8)) {
                                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 8)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(8): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("com.tester.alarmmanager9")) {// && saveData!!.getIsON(9)) {
                                b = intent.extras
                newIntent=Intent(context,GameAlarm::class.java)
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                newIntent.putExtra("Id", 9)
                context!!.startActivity(newIntent)
                Log.d("tagToast", "alarm odpalony, Toast powiadomienie(9): " + b!!.getString("Id"))
            } else if (intent!!.action.equals("lack")) {
                b = intent.extras
                //newIntent=Intent(context,GameAlarm::class.java)
                //newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                //newIntent.putExtra("Id", )
                //context!!.startActivity(newIntent)
                Log.d("tagToast", "isOn=false, lack: " + b!!.getString("Id"))
            }



        //wczytanie danych w razie restartu telefonu
        if(intent!!.action.equals("android.intent.action.BOOT_COMPLETED")){
            val saveData=SaveData(context!!)
            saveData.setAlarm(0)
            saveData.setAlarm(1)
            saveData.setAlarm(2)
            saveData.setAlarm(3)
            saveData.setAlarm(4)
            saveData.setAlarm(5)
            saveData.setAlarm(6)
            saveData.setAlarm(7)
            saveData.setAlarm(8)
            saveData.setAlarm(9)
        }
    }

}
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import games.*

class GameAlarm: AppCompatActivity() {
    var game:String?=null
    var b:Bundle?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_changer)

        b = intent.extras
        val saveData=SaveData(applicationContext)
        game=saveData.getGame(b!!.getInt("Id"))
       // Toast.makeText(this,"GameAlarm stworzony dla Id: "+b!!.getInt("Id").toString(),Toast.LENGTH_SHORT).show()
        newIntent()
    }


    fun newIntent(){
            var intent:Intent?=null
            when (game) {
                "math"->intent=Intent(this,OffMath::class.java)
                "tictactoe"->intent=Intent(this,OffTicTacToe::class.java)
                "normal"->intent=Intent(this,OffNormal::class.java)
                "shake"->intent=Intent(this,OffShake::class.java)
                "clicker"->intent=Intent(this,OffClicker::class.java)
                "among"->intent=Intent(this,OffAmong::class.java)
                "light"->intent=Intent(this,OffLight::class.java)
            }
        Toast.makeText(this,game,Toast.LENGTH_SHORT).show()
        startActivity(intent)
        finish()
    }

}

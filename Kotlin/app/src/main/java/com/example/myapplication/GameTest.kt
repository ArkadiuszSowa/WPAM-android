package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import games.*

class GameTest: AppCompatActivity() {
var game:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_changer)

    }

    fun onClick(view: View) {
        game="lack"
        when (view.id) {
            R.id.ivOffMath -> game ="math"
            R.id.ivOffTicTacToe -> game="tictactoe"
            R.id.ivOffNormal -> game="normal"
            R.id.ivOffShake -> game="shake"
            R.id.ivOffClicker -> game="clicker"
            R.id.ivOffRandom -> game="random"
            R.id.ivOffAmong -> game="among"
            R.id.ivOffLight -> game="light"
        }
        if(game=="random"){
            val a = (1..6).random()
            when(a){
                1->game ="math"
                2->game="tictactoe"
                3->game="normal"
                4->game="shake"
                5->game="clicker"
                6->game="among"
                7->game="light"
            }
        }
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
            //"steps"->intent=Intent(this,OffRandom::class.java)
            "among"->intent=Intent(this,OffAmong::class.java)
            "light"->intent=Intent(this,OffLight::class.java)
        }
        //Toast.makeText(this,game,Toast.LENGTH_SHORT).show()
        intent!!.putExtra("alarmIsOn", false)
        startActivity(intent)
        finish()
    }

}

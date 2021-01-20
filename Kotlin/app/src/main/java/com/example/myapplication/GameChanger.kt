package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class GameChanger : AppCompatActivity() {
    var index:Int?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_changer)
      //  var ListOfAl= activity as ListOfAlarms

//        val ivOffMath:ImageView=findViewById(R.id.ivOffMath)
//        val ivOffNormal:ImageView=findViewById(R.id.ivOffNormal)
//        val ivOffDuck:ImageView=findViewById(R.id.ivOffDuck)
//        val ivOffTicTacToe:ImageView=findViewById(R.id.ivOffTicTacToe)
//        val ivOffAmong:ImageView=findViewById(R.id.ivOffAmong)
//        val ivOffSteps:ImageView=findViewById(R.id.ivOffSteps)
//        val ivOffShake:ImageView=findViewById(R.id.ivOffShake)
//        val ivOffClicker:ImageView=findViewById(R.id.ivOffClicker)


        var b: Bundle? = intent.extras
        index=b!!.getInt("index")

        Log.d("Otrzymany index: ", index.toString())

        //ivOffMath.setOnClickListener {}

    }

    fun onClick(view: View) {
        var game:String="lack"
        when (view.id) {
            R.id.ivOffMath -> game ="math"
            R.id.ivOffTicTacToe -> game="tictactoe"
            R.id.ivOffNormal -> game="normal"
            R.id.ivOffShake -> game="shake"
            R.id.ivOffClicker -> game="clicker"
            R.id.ivOffRandom -> game="random"
            R.id.ivOffAmong -> game="among"
            R.id.ivOffLight -> game="light"//duck zmienione na light
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
        newIntent(game, index!!)
    }


    fun newIntent(game:String, index:Int){
        val intent= Intent(this,ListOfAlarms::class.java)
        intent.putExtra("game", game)
        intent.putExtra("index", index)
        startActivity(intent)
        finish()
    }

}

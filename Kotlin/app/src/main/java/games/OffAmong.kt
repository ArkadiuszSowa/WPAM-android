package games

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.off_among.*


//class OffAmong: Game(),TimerTask(){
class OffAmong: Game(){
    var good=true
    var bot = ArrayList<Int>()
    var player = ArrayList<Int>()
    var step=0
    var buSelectedLast:Button?=null
    var buNextStep:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.off_among)
        buNextStep = findViewById(R.id.buNextStep) as Button
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            seekBarAmong.setProgress(0,true)
        }

        for(i in 0..4) {
             val rnd = (1..9).random()
             Log.d("rnd: ", rnd.toString())
             bot.add(rnd)
        }

        restart()
        playMusic()
    }


    fun restart(){
        player.clear()
        good=true
        step=0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            seekBarAmong.setProgress(step,true)
        }
        buNextStep!!.text = "Next step"
        Log.d("rnd: ", "restart")
    }

    fun nextStep(view: View){
        var buSelected: Button?
        Log.d("rnd step: ", step.toString())
        buSelected=bu1
        if(step<=4) {
            buSelected = when (bot[step]) {
                1 -> bu1
                2 -> bu2
                3 -> bu3
                4 -> bu4
                5 -> bu5
                6 -> bu6
                7 -> bu7
                8 -> bu8
                9 -> bu9
                else -> {
                    bu1
                }
            }
        }

        if(step!=0) buSelectedLast!!.setBackgroundColor(Color.BLACK)

        if(step<=4) {
            buSelectedLast=buSelected
            buSelected!!.setBackgroundColor(Color.GREEN)
        }else{
            buNextStep!!.setText("Your tourn")
        }
        if(step==5)buSelectedLast!!.setBackgroundColor(Color.BLACK)

        if(step<=5){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                seekBarAmong.setProgress(step+1,true)
            }
        }
        step++


    }


    fun buClick(view: View) {
        val buSelected = view as Button
        var cellId = 0
        when(buSelected.id){
            R.id.bu1 -> cellId = 1
            R.id.bu2 -> cellId = 2
            R.id.bu3 -> cellId = 3
            R.id.bu4 -> cellId = 4
            R.id.bu5 -> cellId = 5
            R.id.bu6 -> cellId = 6
            R.id.bu7 -> cellId = 7
            R.id.bu8 -> cellId = 8
            R.id.bu9 -> cellId = 9
        }
        Log.d("rnd", "step w buClick: " + step.toString())
        if(step>4) {
            player.add(cellId)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                seekBarAmong.setProgress(player.size,true)
            }
        }
        Log.d("rnd", "player.size w buClick: " + player.size.toString())
        if(player.size==5){
            check()
        }
    }

    fun check() {
        Log.d("rnd check ", "player.size: " + player.size.toString())
        for (i in 0..4) {
            if ((bot[i] == player[i]) && good) {
                good = true
            } else {
                good = false
            }
        }

            if(good){
                stopAlarm()
            }else{
                restart()
                Toast.makeText(this, "Wrong code. try again", Toast.LENGTH_SHORT).show()
            }
    }




}
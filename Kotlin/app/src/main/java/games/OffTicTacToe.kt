package games

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ListOfAlarms
import com.example.myapplication.R
import kotlinx.android.synthetic.main.off_tictactoe.*
import java.util.*
import kotlin.collections.ArrayList

class OffTicTacToe:Game() {
    var playerWinsCounts = 0
    var botWinsCounts = 0
    var lastCellID=0
    var lastCellIDbool=false
    var betterBuSelected:Button?=null
    var isBeter=false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.off_tictactoe)

        playMusic()
    }

    fun buClick( view:View){
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
        playGame(cellId,buSelected)
    }
    
    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    fun playGame(cellId:Int, buSelected:Button){
        if( activePlayer == 1 ){
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.colorGreen)
            player1.add(cellId)
            activePlayer = 2
            lastCellID=cellId
            autoPlay()
        }else{
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.colorBlue)
            player2.add(cellId)
            activePlayer = 1
            //checkWinner()
        }
        if(checkWinner()==false) {
            buSelected.isEnabled = false
        }
        checkWinner()
    }


    fun checkWinner():Boolean {
        var winer = -1

        // row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winer = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winer = 2
        }
        // row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winer = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winer = 2
        }
        // row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winer = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winer = 2
        }
        // col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winer = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winer = 2
        }
        // col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winer = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winer = 2
        }
        // col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winer = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winer = 2
        }
        // skos 1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winer = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winer = 2
        }
        // skos 2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winer = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winer = 2
        }
        if (winer == 1) {
            playerWinsCounts += 1
            Toast.makeText(this, "Player win the game", Toast.LENGTH_LONG).show()
            restartGame()
            return true
//            if(lastCellIDbool){
//                unblockCell()
//            }
        } else if (winer == 2) {
            botWinsCounts += 1
            Toast.makeText(this, "Bot win the game", Toast.LENGTH_LONG).show()
            restartGame()
            return true
//            if(lastCellIDbool){
//                unblockCell()
//            }
        }
        return false
    }

    fun autoPlay(){
        var emptyCells = ArrayList<Int>()
        for( cellId in 1..9){
            if( !(player1.contains(cellId) || player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }
        //Toast.makeText(this,"emptyCells: "+emptyCells.toString(),Toast.LENGTH_LONG).show()
        if(emptyCells.size==0){
            checkWinner()
            restartGame()
        }else {
            val r = Random()
            val randIndex = r.nextInt(emptyCells.size)
            val cellId = emptyCells[randIndex]

            var buSelected: Button?
            buSelected = when (cellId) {
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
            inteligent_bot()
            if(isBeter){
                buSelected=betterBuSelected
            }
            isBeter=false
            playGame(cellId, buSelected!!)
        }
    }
    

    fun restartGame() {
        activePlayer = 1
        player1.clear()
        player2.clear()
        for (cellId in 1..9) {
            var buSelected: Button? = when (cellId) {
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
            buSelected!!.text = ""
            buSelected!!.setBackgroundResource(R.color.colorWhiteText)
            buSelected!!.isEnabled = true
        }
        lastCellIDbool=true
        unblockCell()
        var neededWins=1+botWinsCounts-playerWinsCounts
        textView.setText("Yot need "+neededWins.toString()+" to stop alarm")
        if(neededWins==0){
            stopAlarm()
        }
//        val intent= Intent(this, OffTicTacToe::class.java)
//        intent.putExtra("neededWins", neededWins)
//        intent.putExtra("alarmIsOn", true)
//        startActivity(intent)
//        finish()

    }

    fun unblockCell() {
        lastCellIDbool=false
        var buSelected: Button? = when (lastCellID) {
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
        //Toast.makeText(this,"unblockLastCell:"+lastCellID.toString(),Toast.LENGTH_SHORT).show()
        buSelected!!.isEnabled = true
    }

    fun inteligent_bot(){
        if(player2.contains(1)&&player2.contains(3)&& !player1.contains(2)) {
            betterBuSelected = bu2
            isBeter=true
        }
        if(player2.contains(1)&&player2.contains(2)&& !player1.contains(3)) {
            betterBuSelected = bu3
                    isBeter=true
        }
        if(player2.contains(2)&&player2.contains(3)&& !player1.contains(1)) {
            betterBuSelected = bu1
                    isBeter=true
        }
        if(player2.contains(4)&&player2.contains(5)&& !player1.contains(6)) {
            betterBuSelected = bu6
                    isBeter=true
        }
        if(player2.contains(5)&&player2.contains(6)&& !player1.contains(4)) {
            betterBuSelected = bu4
                    isBeter=true
        }
        if(player2.contains(4)&&player2.contains(6)&& !player1.contains(5)) {
            betterBuSelected = bu5
                    isBeter=true
        }
        if(player2.contains(8)&&player2.contains(9)&& !player1.contains(7)) {
            betterBuSelected = bu7
                    isBeter=true
        }
        if(player2.contains(7)&&player2.contains(9)&& !player1.contains(8)) {
            betterBuSelected = bu8
                    isBeter=true
        }
        if(player2.contains(7)&&player2.contains(8)&& !player1.contains(9)) {
            betterBuSelected = bu9
                    isBeter=true
        }
        //column
        if(player2.contains(4)&&player2.contains(7)&& !player1.contains(1)) {
            betterBuSelected = bu1
                    isBeter=true
        }
        if(player2.contains(1)&&player2.contains(7)&& !player1.contains(4)) {
            betterBuSelected = bu4
                    isBeter=true
        }
        if(player2.contains(1)&&player2.contains(4)&& !player1.contains(7)) {
            betterBuSelected = bu7
                    isBeter=true
        }
        //column2
        if(player2.contains(5)&&player2.contains(8)&& !player1.contains(2)) {
            betterBuSelected = bu2
                    isBeter=true
        }
        if(player2.contains(2)&&player2.contains(8)&& !player1.contains(5)) {
            betterBuSelected = bu5
                    isBeter=true
        }
        if(player2.contains(2)&&player2.contains(5)&& !player1.contains(8)) {
            betterBuSelected = bu8
                    isBeter=true
        }
        //column3
        if(player2.contains(6)&&player2.contains(9)&& !player1.contains(3)) {
            betterBuSelected = bu3
                    isBeter=true
        }
        if(player2.contains(3)&&player2.contains(9)&& !player1.contains(6)) {
            betterBuSelected = bu6
                    isBeter=true
        }
        if(player2.contains(6)&&player2.contains(3)&& !player1.contains(9)) {
            betterBuSelected = bu9
                    isBeter=true
        }

        //skos1
        if(player2.contains(5)&&player2.contains(9)&& !player1.contains(1)) {
            betterBuSelected = bu1
                    isBeter=true
        }
        if(player2.contains(1)&&player2.contains(9)&& !player1.contains(5)) {
            betterBuSelected = bu5
                    isBeter=true
        }
        if(player2.contains(1)&&player2.contains(5)&& !player1.contains(9)) {
            betterBuSelected = bu9
                    isBeter=true
        }
        //skos2
        if(player2.contains(5)&&player2.contains(7)&& !player1.contains(3)) {
            betterBuSelected = bu3
                    isBeter=true
        }
        if(player2.contains(3)&&player2.contains(7)&& !player1.contains(5)) {
            betterBuSelected = bu5
                    isBeter=true
        }
        if(player2.contains(5)&&player2.contains(3)&& !player1.contains(7)) {
            betterBuSelected = bu7
                    isBeter=true
        }
    }

    }
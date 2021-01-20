package games

import android.os.Bundle
import android.widget.EditText
import com.example.myapplication.R
import kotlinx.android.synthetic.main.off_math.*


class OffMath: Game(){
    var saveOperator:String?=null
    var saveEquation:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.off_math)
        val a = (0..20).random()
        val b = (0..10).random()
        var op0=chooseOperator(a, b)
        val saveOperator1=saveOperator
        val c = (0..10).random()
        var op1=chooseOperator(op0, c)
        if(saveOperator=="x"){
            if(saveOperator1=="+"){
                op1=a+(b*c)
            }else if (saveOperator1=="-"){
                op1=a-(b*c)
            }
        }

        playMusic()

        saveEquation=a.toString()+saveOperator1+b.toString()+saveOperator+c.toString()
        tvEquation.setText(saveEquation)

        var solved:String?=null
        buSolved.setOnClickListener {
           solved=editText.getText().toString()
            if(solved==op1.toString()){
                stopAlarm()
            }
        }

    }

    fun chooseOperator(a: Int, b: Int): Int {
        var wynik=0
        val rnds = (0..5).random()
        when(rnds){
            1, 2 -> {
                wynik = plus(a, b)
                saveOperator = "+"
            }
            3, 4 -> {
                wynik = minus(a, b)
                saveOperator = "-"
            }
            5 -> {
                wynik = mno(a, b)
                saveOperator = "x"
            }
        }
        return wynik
    }

    fun plus(a: Int, b: Int):Int{
        return a+b
    }

    fun minus(a: Int, b: Int):Int{
        return a-b
    }

    fun mno(a: Int, b: Int):Int{
        return a*b
    }



}
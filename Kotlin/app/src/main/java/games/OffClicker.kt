package games

import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import kotlinx.android.synthetic.main.off_clicker.*


class OffClicker: Game(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.off_clicker)

        val currentLayout = findViewById(R.id.off_clicker) as ConstraintLayout
        currentLayout.setBackgroundColor(Color.rgb(255, 0, 0))

        playMusic()

        var clickAmount=0
        ivClick.setOnClickListener {
            clickAmount++
            currentLayout.setBackgroundColor(Color.rgb(255-3*clickAmount, 3*clickAmount, 2*clickAmount))
            if(clickAmount==50){
                stopAlarm()
            }
        }






    }






}
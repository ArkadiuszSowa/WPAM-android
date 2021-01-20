package games

import android.os.Bundle
import android.widget.SeekBar
import com.example.myapplication.R


class OffNormal : Game() {
    var bar1:Boolean?=null
    var bar2:Boolean?=null
    var bar3:Boolean?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.off_normal)
        playMusic()
//        switch2.setOnClickListener {
//            finish()
//        }

        val seekBar1 = findViewById<SeekBar>(R.id.seekBar1)
        seekBar1?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar1: SeekBar, progress: Int, fromUser: Boolean) {
            }
            override fun onStartTrackingTouch(seekBar1: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar1: SeekBar) {
                if(seekBar1.progress==10){
                    bar1=true
                }else{
                    bar1=false
                }
                if(bar1==true&&bar2==true&&bar3==true){
                    stopAlarm()
                }
            }
        })

        val seekBar2 = findViewById<SeekBar>(R.id.seekBar2)
        seekBar2?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar2: SeekBar, progress: Int, fromUser: Boolean) {
            }
            override fun onStartTrackingTouch(seekBar2: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar2: SeekBar) {
                if(seekBar2.progress==10){
                    bar2=true
                }else{
                    bar2=false
                }
                if(bar1==true&&bar2==true&&bar3==true){
                    stopAlarm()
                }
            }
        })

        val seekBar3 = findViewById<SeekBar>(R.id.seekBar3)
        seekBar3?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar3: SeekBar, progress: Int, fromUser: Boolean) {
            }
            override fun onStartTrackingTouch(seekBar3: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar3: SeekBar) {
                if(seekBar3.progress==10){
                    bar3=true
                }else{
                    bar3=false
                }
                if(bar1==true&&bar2==true&&bar3==true){
                    stopAlarm()
                }
            }
        })

    }

}
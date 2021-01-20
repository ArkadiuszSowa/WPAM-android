package com.example.myapplication

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.nfc.NdefRecord.createUri
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {
    var saveData:SaveData?=null
    var wiedzmin:Switch?=null
    var bell:Switch?=null
    var forest:Switch?=null
    var sea:Switch?=null
    var stronger:Switch?=null
    var mp: MediaPlayer?=null
    var mAudioManager: AudioManager?=null
   // var mediaUri: Uri?=null
    var musicIsOn:Boolean?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        musicIsOn=false

        wiedzmin=findViewById(R.id.sWiedzmin) as Switch
        bell=findViewById(R.id.sBell) as Switch
        forest=findViewById(R.id.sForest) as Switch
        sea=findViewById(R.id.sSea) as Switch
        stronger=findViewById(R.id.sStronger) as Switch

        saveData=SaveData(applicationContext)
        setSwitchesOff()

        val song=saveData!!.getSong()
        //Toast.makeText(this, "SaveData song: " + saveData!!.getSong(), Toast.LENGTH_SHORT).show()
        when(song){
            "wiedzmin" -> wiedzmin!!.setChecked(true)
            "bell" -> bell!!.setChecked(true)
            "forest" -> forest!!.setChecked(true)
            "sea" -> sea!!.setChecked(true)
            "stronger" -> stronger!!.setChecked(true)
        }

        val sbVolume = findViewById<SeekBar>(R.id.sbVolume)
        sbVolume?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sbVolume: SeekBar, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(sbVolume: SeekBar) {
            }

            override fun onStopTrackingTouch(sbVolume: SeekBar) {
                saveData!!.saveVol(sbVolume.progress)
//                Toast.makeText(this, "SaveData volume: " +saveData!!.getVolume().toString(),Toast.LENGTH_SHORT).show()
            }
        })


    }

    fun buSwitch(view: View){
        if(musicIsOn!!){
            mp!!.stop()
        }
        //Toast.makeText(this, "SaveData song: " +saveData!!.getSong(),Toast.LENGTH_SHORT).show()
        setSwitchesOff()
        val buSelected = view as Switch
        when(buSelected.id){
            R.id.sWiedzmin -> {
                saveData!!.saveSong("wiedzmin")
                wiedzmin!!.setChecked(true)
            }
            R.id.sBell -> {
                saveData!!.saveSong("bell")
                bell!!.setChecked(true)
            }
            R.id.sForest -> {
                saveData!!.saveSong("forest")
                forest!!.setChecked(true)
            }
            R.id.sSea -> {
                saveData!!.saveSong("sea")
                sea!!.setChecked(true)
            }
            R.id.sStronger -> {
                saveData!!.saveSong("stronger")
                stronger!!.setChecked(true)
            }
        }
        //Toast.makeText(this, "SaveData song: " +saveData!!.getSong(),Toast.LENGTH_SHORT).show()
        playMusic()
    }

    fun playMusic(){
        val volume=saveData!!.getVolume()
        setMediaUri()

            try {
                //Toast.makeText(this, "meida uri: " +mediaUri,Toast.LENGTH_SHORT).show()
                mAudioManager= getSystemService(AUDIO_SERVICE) as AudioManager?
                mAudioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC,volume,0)
                mp!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                //mp= MediaPlayer.create(this, mediaUri!!)
                mp!!.setLooping(true)
                mp!!.start()
                musicIsOn=true
            }catch (ex: Exception){}
    }

    fun setMediaUri() {
        val song = saveData!!.getSong()

        when (song) {
            "wiedzmin" -> mp = MediaPlayer.create(this, R.raw.wiedzmin)
            "bell" -> mp = MediaPlayer.create(this, R.raw.bell)
            "forest" -> mp = MediaPlayer.create(this, R.raw.forest)
            "sea" -> mp = MediaPlayer.create(this, R.raw.sea)
            "stronger" -> mp = MediaPlayer.create(this, R.raw.stronger)
            else -> {
                mp = MediaPlayer.create(this, R.raw.wiedzmin)
            }
        }

//        when(song){
//            "wiedzmin"->mediaUri= Uri.parse("R.raw.wiedzmin")
//            "bell"->mediaUri= Uri.parse("R.raw.bell")
//            "forest"->mediaUri= Uri.parse("R.raw.forest")
//            "sea"->mediaUri= Uri.parse("R.raw.sea")
//            "stronger"->mediaUri= Uri.parse("R.raw.stronger")
//            else->{
//                mediaUri= Uri.parse("R.raw.wiedzmin")
//            }
//        }
    }


    fun setSwitchesOff(){
        wiedzmin!!.setChecked(false)
        bell!!.setChecked(false)
        forest!!.setChecked(false)
        sea!!.setChecked(false)
        stronger!!.setChecked(false)
    }


   override
    fun onPause() {
        super.onPause()
       if(musicIsOn!!) {
           mp!!.stop()
       }
    }


}
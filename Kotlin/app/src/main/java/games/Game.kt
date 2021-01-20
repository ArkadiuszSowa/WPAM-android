package games

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.SaveData

open class Game: AppCompatActivity() {
    var mp: MediaPlayer?=null
    var isRunning=false
    var vibrator:Vibrator?=null
    val pattern = longArrayOf(0, 500, 1000)
    var mAudioManager:AudioManager?=null
    var originalVolume=0
    var saveData: SaveData?=null
   // var mediaUri: Uri?=null //nie dziala Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveData=SaveData(applicationContext)
    }


    fun playMusic(){
        val volume=saveData!!.getVolume()
        setMediaUri()
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator!!.vibrate(pattern, 0)
        if(isRunning==false){
            isRunning=true
            try {
                mAudioManager= getSystemService(AUDIO_SERVICE) as AudioManager?
                originalVolume = mAudioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC)
                //println("MAX SOUND: "+mAudioManager!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toString()) //15
                mAudioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
                //mp= MediaPlayer.create(this, R.raw.wiedzmin)
                mp!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mp!!.setLooping(true)
                mp!!.start()
            }catch (ex: Exception){}
        }
    }

    fun setMediaUri() {
        val song = saveData!!.getSong()

        when (song) {
            "wiedzmin" ->mp = MediaPlayer.create(this, R.raw.wiedzmin)
            "bell" -> mp = MediaPlayer.create(this, R.raw.bell)
            "forest" -> mp = MediaPlayer.create(this, R.raw.forest)
            "sea" -> mp = MediaPlayer.create(this, R.raw.sea)
            "stronger" -> mp = MediaPlayer.create(this, R.raw.stronger)
            else -> {
                mp = MediaPlayer.create(this, R.raw.wiedzmin)
            }
        }
    }

    fun stopAlarm(){
        if(isRunning) {
            isRunning=false
            mp!!.stop()
            mp!!.release()
            vibrator!!.cancel()
            mAudioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume, 0);
        }
        finish()
    }

}
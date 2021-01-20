package games

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R

class OffLight: Game(),SensorEventListener{
    var sensor:Sensor?=null
    var sensorManager:SensorManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.off_light)

        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        playMusic()
    }

    override fun onSensorChanged(event: SensorEvent?) {
//        Log.d("light val0 z Offligft: ", event!!.values[0].toString())
//        print("light sensor work: "+ event!!.values[0].toString())

        if(event!!.values[0]>15000){
            Toast.makeText(this,"light val: "+ event!!.values[0].toString(),Toast.LENGTH_SHORT).show()
            stopAlarm()
            sensorManager!!.unregisterListener(this)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

//    override fun onRestart() {
//        super.onRestart()
//        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        sensorManager!!.unregisterListener(this)
//    }

}
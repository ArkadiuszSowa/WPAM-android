package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.myapplication.ListOfAlarms.AlarmAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_alarms.*
import kotlinx.android.synthetic.main.one_alarm.view.*
import java.util.ArrayList

class ListOfAlarms : AppCompatActivity(){
    var listOfAlarms=ArrayList<OneAlarm>()//lista potrzebna do wyświetlania listy alarmów
    var adapter: AlarmAdapter?=null
    var saveData:SaveData?=null
    var fm: FragmentManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_alarms)

        saveData=SaveData(applicationContext)

        load_alarms(saveData!!)
        adapter= AlarmAdapter(this, listOfAlarms)
        tvListAlarms?.adapter=adapter

        //do otrzymywania zmiennej game
        var b: Bundle? = intent!!.extras
        if(b!!.getString("game")!="lack") {
            setGame(b)
        }


    }

    fun setGame(b:Bundle) {
        val index=b!!.getInt("index")
        val game=b!!.getString("game")
        Log.d("tagToast", "Otrzymana gra i index: " + game + "  "+index.toString())
        listOfAlarms[index].game=game
        saveGame(index,game.toString())
        adapter!!.notifyDataSetChanged()
    }


    fun load_alarms(saveData: SaveData){
        for (i in 0..9){//0..9 bo takie mam możliwe indeksy alarmów
            if(saveData.getHour(i)!=100){
                listOfAlarms.add(OneAlarm(saveData.getHour(i), saveData.getMinute(i),  i, saveData.getGame(i).toString(),saveData.getIsON(i)))
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    //przycisk dodwania w menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addAlarm->
            {
                if(!add())// add zwraca false dla zapełnionych 10 alarmów, więc "!fals" da true i zobaczymy toast
                    Toast.makeText(this,"Max number of alarms",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //sortowanie tablicy po usunięciu i ponowny zapis
    fun delete(index: Int){
        //trzeba przepisać plik od nowa
        val count=listOfAlarms.size
        for (i in index..count-2) {// -2 bo za rozmiar idzie -1 i za usuniety element -1 (ten +1 by nie pykło innaczk)
            listOfAlarms[i] = listOfAlarms[i + 1]
            saveData!!.SaveData(listOfAlarms[i].hour!!, listOfAlarms[i].minute!!, i, listOfAlarms[i].game!!,listOfAlarms[i].isON!!)//zapisujemy nasze nowe dane
        }
        listOfAlarms.removeAt(count-1)
        saveData!!.SaveData(100,0,count-1,"tictactoe",false)//wyczyszczenie ostatniego alarmu z pliku
        adapter!!.notifyDataSetChanged()

    }

    fun add():Boolean {  //trzeba przepisać plik od nowa
        if(saveData!!.getHour(9)!=100){
            return false //tylko 10 alarmow dostępne
        }else{
            val oneAlarm = OneAlarm(0, 0,  0, "tictactoe",true)
            val count = listOfAlarms.size
            listOfAlarms.add(count, oneAlarm)
            for (i in 0..count - 1) {
                listOfAlarms[count - i] = listOfAlarms[count - 1 - i]
                //przepisywanie pliku od nowa
                saveData!!.SaveData(listOfAlarms[count - i].hour!!, listOfAlarms[count - i].minute!!, count - i,listOfAlarms[count - i].game!!,listOfAlarms[count - i].isON!!)
            }
            listOfAlarms[0]=oneAlarm
            val a = (1..7).random()
            var Rgame="tictactoe"
            when(a){
                1->Rgame ="math"
                2->Rgame="lihgt"
                3->Rgame="normal"
                4->Rgame="shake"
                5->Rgame="clicker"
                6->Rgame="among"
            }
            saveData!!.SaveData(listOfAlarms[0].hour!!, listOfAlarms[0].minute!!, 0, Rgame,true)
            adapter!!.notifyDataSetChanged()
            return true
        }
    }

    fun setIsOn(index: Int,isOn:Boolean){
        listOfAlarms[index].isON=isOn
        saveIsON(index,isOn)
        adapter!!.notifyDataSetChanged()
    }


//Wyświetalnie całej listy
    inner class AlarmAdapter(context: Context, var listOfAlarms: ArrayList<OneAlarm>) : BaseAdapter() {
    var context: Context?= context

    override fun getCount(): Int {
            return listOfAlarms.size
        }

        override fun getItem(index: Int): OneAlarm {
            return listOfAlarms[index]
        }

        override fun getItemId(index: Int): Long {
            return index.toLong()
        }

        override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
            val alarm=listOfAlarms[index]
            var inflator= context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView=inflator.inflate(R.layout.one_alarm, null)

            //Wyświetlanie godziny
            if(alarm.minute!! <10){
                myView.tvTime.text = alarm.hour.toString() + ":0" + alarm.minute.toString()
            }else {
                myView.tvTime.text = alarm.hour.toString() + ":" + alarm.minute.toString()
            }


            if(alarm.game=="math") {
                myView.ivGame.setImageResource(R.drawable.math)
            } else{
                myView.ivGame.setImageResource(R.drawable.tictactoe)
            }

            when (alarm.game) {
                "math" -> myView.ivGame.setImageResource(R.drawable.math)
                "tictactoe" -> myView.ivGame.setImageResource(R.drawable.tictactoe)
                "normal" -> myView.ivGame.setImageResource(R.drawable.ic_trending_flat_black_24dp)
                "shake" -> myView.ivGame.setImageResource(R.drawable.shake_phone)
                "clicker" -> myView.ivGame.setImageResource(R.drawable.ic_radio_button_checked_black_24dp)
                "among" -> myView.ivGame.setImageResource(R.drawable.ic_keyboard_black_24dp)
                "light" -> myView.ivGame.setImageResource(R.drawable.ic_highlight_black_24dp)
            }

            if(alarm.isON!!) {
                myView.buONOF.setChecked(true)
            }else{
                myView.buONOF.setChecked(false)
            }


            myView.buONOF.setOnCheckedChangeListener { _, isChecked ->//TODO przycisk od anulowania alarmu
                if (isChecked) {
                    alarm.isON=true//nie wiem czy to potrzebne, raczej to jest taka zmienna ze nic mi to nie da
                    //setIsOn(index,true)
                    //Toast.makeText(context,"isOn=true index: "+ index.toString(), Toast.LENGTH_SHORT).show()
                } else {
                    // The toggle is disabled
                    alarm.isON=false
                   // setIsOn(index,false)
                    //Toast.makeText(context,"isOn=false index: "+ index.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            myView.ivGame.setOnClickListener {
                val intent= Intent(this@ListOfAlarms,GameChanger::class.java)
                intent.putExtra("index", index)
                startActivity(intent)
                finish()
            }

            myView.tvTime.setOnClickListener {
                BuSetTime(index)
            }

            myView.buDelete.setOnClickListener {
                delete(index)
            }
            return myView
        }

    }

    //bardziej saveTime, ale taka była pierwotna nazwa wiec niech zostanie
    fun SetTime(Hours: Int, Minute: Int, Id: Int){
////TODO sortowanie alarmow, mam pozycje nowego alarmu, trzeba jeszcze przepisać te o mniejszej wartosci o jedna pozycje w dol
//        var newId=0
//        if(listOfAlarms.size!=1) {
//            while (Hours > listOfAlarms[newId + 1].hour!!.toInt()) {
//                newId++
//                if(newId==listOfAlarms.size-1)break
//            }
//            if(Hours == listOfAlarms[newId + 1].hour!!.toInt()){
//                while (Minute > listOfAlarms[newId + 1].minute!!.toInt()) {
//                    newId++
//                    if (newId == listOfAlarms.size - 1) break
//                }
//            }
//        }


        saveData!!.SaveData(Hours, Minute, Id, listOfAlarms[Id].game!!, listOfAlarms[Id].isON!!)
        finish()                       //odświeżanie wyglądu listy
        startActivity(getIntent())
    }

    fun saveGame(Id: Int,game:String){
        saveData!!.SaveData(listOfAlarms[Id].hour!!, listOfAlarms[Id].minute!!, Id, game, listOfAlarms[Id].isON!!)
        Log.d("setGame ", "Gra zapisana: "+Id.toString())
    }

    fun saveIsON(Id: Int,isOn: Boolean){
        saveData!!.SaveData(listOfAlarms[Id].hour!!, listOfAlarms[Id].minute!!, Id,listOfAlarms[Id].game!!, isOn)
        Log.d("setIsON ", "isOn zapisane: "+Id.toString())
    }

    var popTime:PopTime?=null

    fun BuSetTime(cellId:Int){
        popTime= PopTime(cellId)
        fm=supportFragmentManager
        popTime!!.show(fm, "Select time")
    }







}
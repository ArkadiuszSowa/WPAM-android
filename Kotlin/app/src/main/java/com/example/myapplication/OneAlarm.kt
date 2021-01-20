package com.example.myapplication

class OneAlarm{
    var hour:Int?=null
    var minute:Int?=null
    var game:String?=null
    var isON:Boolean?=null
    var cellId:Int?=null

    constructor(hour:Int,minute:Int,cellId:Int, game:String, isOn:Boolean) {
        this.hour = hour
        this.minute = minute
        this.game = game
        this.cellId=cellId
        this.isON=isOn
    }



}
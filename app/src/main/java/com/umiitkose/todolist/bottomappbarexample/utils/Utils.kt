package com.umiitkose.todolist.bottomappbarexample.utils

import android.content.Context
import android.widget.Toast

fun toast(data:String,  context: Context){
    Toast.makeText( context ,data, Toast.LENGTH_LONG).show()
}
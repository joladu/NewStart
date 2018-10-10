package com.jola.shengfan.skills


/**
 * Created by lenovo on 2018/10/8.
 */
fun main(args : Array<String>){

    val sumLambda : (Int,Int) -> Int = {x,y -> x+y}
    System.out.print(sumLambda(3,5))

    var age : String? = "23"

    age!!.toInt()//抛异常

    var age1 = age?.toInt()//为空返回null

    age?.toInt() ?: -1

    var obj : Any = 1
    if (obj is String){}

    for (i in 1..10){

    }





}
package com.jola.shengfan.skills.kotlin_helloworld

import android.util.Log.println

/**
 * Created by lenovo on 2018/10/8.
 */
class HelloWord{}
fun text(){}

fun sum(a : Int,b : Int): Int{
    return a+b
}

fun sum1(a :Int,b:Int) = a+b

//fun main(args : Array<String>){
//    println("Hello World")
//}

fun main(args : Array<String>){

//    sum(2,5)
    System.out.println("sum(2,5)="+sum(2,5) )

    sum1(3,5)

    val sumlambda : (Int ,Int) -> Int = {x,y -> x+y}


}
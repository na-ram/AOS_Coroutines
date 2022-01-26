package com.os_companies.aos_coroutines

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlin.system.measureTimeMillis

class suspendExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).async {

            val time = measureTimeMillis {

                val i1 = async {
                    subRoutine1()
                }
                val i2 = async {
                    subRoutine2()
                }

                Log.d("Coroutine", "Sum : ${i1.await() + i2.await()}")

            }

            Log.d("Coroutine", "Completed in ${time}ms")

        }

    }

//    fun subRoutine1(): Int {
//        Thread.sleep(3000)
//        Log.d("subRoutine1", "After 3s in ${Thread.currentThread().name}")
//        return 20
//
//    }
//
//    fun subRoutine2(): Int {
//        Thread.sleep(1000)
//        Log.d("subRoutine2", "After 1s in ${Thread.currentThread().name}")
//        return 50
//    }

    fun subRoutine1(): Int {
        Thread.sleep(3000)
        Log.d("subRoutine1", "After 3s in ${Thread.currentThread().name}")
        return 20

    }

    fun subRoutine2(): Int {
        Thread.sleep(1000)
        Log.d("subRoutine2", "After 1s in ${Thread.currentThread().name}")
        return 50
    }

}
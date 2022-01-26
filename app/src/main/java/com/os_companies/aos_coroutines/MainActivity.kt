package com.os_companies.aos_coroutines

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val tvCoroutineValue: TextView by lazy {
        findViewById(R.id.tvCoroutineValue)
    }

    private val btnCoroutineCancel: Button by lazy {
        findViewById(R.id.btnCoroutineCancel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {

            tvCoroutineValue.text = "Coroutines를 통한 값 넣기 (GlobalScope)"

        }

        CoroutineScope(Dispatchers.Default).async {

            tvCoroutineValue.text = "Coroutine을 통해 값 넣기 (CoroutineScope)"

        }

        val job = CoroutineScope(Dispatchers.Default).launch {

            for(i in 0..10) {
                delay(1000)
                Log.d("Coroutines", "i : $i")
            }
        }

        btnCoroutineCancel.setOnClickListener {

            job.cancel()

        }

        CoroutineScope(Dispatchers.Default).launch {

            launch {
                for(i in 0..5) {
                    delay(1000)
                    Log.d("Coroutines", "i : $i")
                }
            }.join()

            launch {
                for(j in 6..10) {
                    delay(1000)
                    Log.d("Coroutines", "j : $j")
                }
            }

        }

        CoroutineScope(Dispatchers.Default).launch {

            val deferred1 = async {
                delay(1000)
                600
            }

            val deferred2 = async {
                delay(1500)
                200
            }

            Log.d("Coroutines", "${deferred1.await()}, ${deferred2.await()}")

        }


        CoroutineScope(Dispatchers.Main).launch {

            tvCoroutineValue.text = "Coroutines를 통한 값 넣기(CoroutineScope)"

            withContext(Dispatchers.IO) {
                // 네트워크, DB 작업 등
            }

        }

    }

}
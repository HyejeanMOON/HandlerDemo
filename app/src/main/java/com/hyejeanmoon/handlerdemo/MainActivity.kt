package com.hyejeanmoon.handlerdemo

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejeanmoon.handlerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.handlerMessage.setOnClickListener {
            handlerMessage()
        }

        binding.handlerRunnable.setOnClickListener {
            handlerRunnable()
        }

        binding.handlerDelayRunnable.setOnClickListener {
            handlerDelayRunnable()
        }

        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    1 -> {
                        Toast.makeText(applicationContext, msg.obj.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                    else -> {
                        Toast.makeText(applicationContext, "others", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {

        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
        
    }

    private fun handlerMessage() {
        Thread(Runnable {
            val message = Message()
            message.what = 1
            message.obj = "handler message"

            handler.sendMessage(message)
        }).start()
    }

    private fun handlerRunnable() {
        Thread(Runnable {
            handler.post(Runnable {
                Toast.makeText(applicationContext, "handler runnable", Toast.LENGTH_LONG).show()
            })
        }).start()
    }

    private fun handlerDelayRunnable() {
        Thread(Runnable {
            handler.postDelayed(Runnable {
                Toast.makeText(applicationContext, "handler delayRunnable", Toast.LENGTH_LONG)
                    .show()
            }, 2000)
        }).start()
    }
}

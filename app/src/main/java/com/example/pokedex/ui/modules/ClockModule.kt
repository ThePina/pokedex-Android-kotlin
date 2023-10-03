package com.example.pokedex.ui.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.pokedex.R
import java.util.*

class ClockModule(context: Context) {
    private val clockView: View
    private var clockThread: Thread? = null
    private var isClockRunning = false

    init {
        val inflater = LayoutInflater.from(context)
        clockView = inflater.inflate(R.layout.clock_layout, null)
    }

    fun getView(): View {
        return clockView
    }

    //iniciar hilo cuando se inicie el reloj, y ejecutar la funcion update cada un segundo
    fun startClock() {
        if (!isClockRunning) {
            isClockRunning = true
            clockThread = Thread {
                while (isClockRunning) {
                    clockView.post {
                        updateClock()
                    }
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {

                    }
                }
            }
            clockThread?.start()
        }
    }


    fun stopClock() {
        isClockRunning = false
        clockThread?.interrupt()
    }

    //funcion encargarda de actualizar los valores visuales del reloj una vez por segundo
    private fun updateClock() {
        val currentTimeMillis = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTimeMillis

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        val formattedTime = String.format("%02d:%02d:%02d", hour, minute, second)


        val hourTextView = clockView.findViewById<TextView>(R.id.hourTextView)
        hourTextView.text = formattedTime
    }

}

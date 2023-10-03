package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.pokedex.ui.fragments.PokemonListFragment
import com.example.pokedex.ui.fragments.modules.ClockModule

class MainActivity : AppCompatActivity() {
    private lateinit var clockModule: ClockModule

    override fun onResume() {
        super.onResume()
        clockModule.startClock() // Iniciar el reloj cuando se reanuda la actividad
    }

    override fun onPause() {
        super.onPause()
        clockModule.stopClock() // Detener el reloj cuando se pausa la actividad
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // obtener fragment
        val myFragment = PokemonListFragment()

        val fragmentManager = supportFragmentManager


        clockModule = ClockModule(this)

        val clockContainer = findViewById<FrameLayout>(R.id.clockFrame)
        clockContainer.addView(clockModule.getView())


        // insertar el fragment en el fragmentContainer de la vista
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame, myFragment)
        transaction.commit()



    }
}
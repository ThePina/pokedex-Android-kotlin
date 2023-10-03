package com.example.pokedex.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.FrameLayout
import com.example.pokedex.R
import com.example.pokedex.ui.fragments.PokemonListFragment
import com.example.pokedex.ui.modules.ClockModule

class MainActivity : AppCompatActivity() {
    private lateinit var clockModule: ClockModule

    override fun onResume() {
        super.onResume()
        clockModule.startClock() // Iniciar el reloj
    }

    override fun onPause() {
        super.onPause()
        clockModule.stopClock() // Detener el reloj
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //quitar focus del editText de busqueda
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        // obtener fragment
        val myFragment = PokemonListFragment()

        val fragmentManager = supportFragmentManager


        //instanciar el modulo del reloj
        clockModule = ClockModule(this)

        //asignar el container que tendra al reloj
        val clockContainer = findViewById<FrameLayout>(R.id.clockFrame)
        clockContainer.addView(clockModule.getView())


        // insertar el fragment en el fragmentContainer de la vista para la lista
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame, myFragment)
        transaction.commit()



    }
}
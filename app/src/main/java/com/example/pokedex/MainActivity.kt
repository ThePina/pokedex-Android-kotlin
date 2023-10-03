package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedex.ui.fragments.PokemonList

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // obtener fragment
        val myFragment = PokemonList()

        val fragmentManager = supportFragmentManager

        // insertar el fragment en el fragmentContainer de la vista
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame, myFragment)
        transaction.commit()

    }
}
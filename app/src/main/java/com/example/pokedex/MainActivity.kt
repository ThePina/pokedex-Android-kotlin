package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.ui.PokemonList

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
package com.example.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R

class PokemonDetailFragment : Fragment() {
    private lateinit var name: TextView
    private var pokemon: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        name=view.findViewById(R.id.namePokemon)

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name.setText(pokemon)
    }

    companion object {
        @JvmStatic
        fun newInstance(pokemon: String) =
            PokemonDetailFragment().apply {
                this.pokemon =pokemon
            }
    }
}
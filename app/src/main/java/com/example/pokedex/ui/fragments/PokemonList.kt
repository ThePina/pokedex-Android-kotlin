package com.example.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonItemListAdapter
import com.example.pokedex.models.PokemonItemList


class PokemonList : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PokemonItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        recyclerView = view.findViewById(R.id.reciclerViewPokemon)



        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val pokemonList = mutableListOf(
            PokemonItemList("Pikachu",""),
            PokemonItemList("Charizard",""),
            PokemonItemList("Bulbasaur",""),
            PokemonItemList("Squirtle",""),
            PokemonItemList("Mewtwo",""),
        )


        adapter = PokemonItemListAdapter(requireContext(), pokemonList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

    }

    companion object {
        @JvmStatic
        fun newInstance() = PokemonList()
    }
}
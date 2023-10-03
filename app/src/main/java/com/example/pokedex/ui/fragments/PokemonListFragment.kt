package com.example.pokedex.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonItemListAdapter
import com.example.pokedex.models.PokemonListItemResponse
import com.example.pokedex.models.PokemonListResponse
import com.example.pokedex.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PokemonListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PokemonItemListAdapter
    private val pokemonRepository = PokemonRepository()
    private lateinit var progressBarLayout: FrameLayout
    private lateinit var editTextSearch: EditText
    private var originalPokemonList: List<PokemonListItemResponse> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        recyclerView = view.findViewById(R.id.reciclerViewPokemon)
        progressBarLayout=view.findViewById(R.id.progressBarLayout)
        editTextSearch=view.findViewById<EditText>(R.id.editTextSearch)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = PokemonItemListAdapter(requireContext(), mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokemonListResponse = withContext(Dispatchers.IO) {
                    pokemonRepository.getPokemonList()
                }

                val pokemonList = pokemonListResponse
                originalPokemonList = pokemonList

                progressBarLayout.visibility=View.GONE
                adapter.updateData(pokemonList)


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val query = s.toString().trim()
                val filteredList = originalPokemonList.filter { pokemon ->

                    pokemon.name.contains(query, true)
                }
                adapter.updateData(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = PokemonListFragment()
    }
}
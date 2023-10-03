package com.example.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class PokemonDetailFragment : Fragment() {
    private lateinit var name: TextView
    private lateinit var types: TextView

    private lateinit var image: ImageView
    private var pokemon: String? = null
    private val pokemonRepository = PokemonRepository()

    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL) // Opciones de almacenamiento en caché
        .centerCrop() // Escala la imagen para que se ajuste al ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        name=view.findViewById(R.id.namePokemon)
        image=view.findViewById(R.id.imageView)
        types=view.findViewById(R.id.types)



        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokemonData = withContext(Dispatchers.IO) {
                    pokemon?.let { pokemonRepository.getPokemonDetails(it) }
                }
                if (pokemonData != null) {
                    name.setText(pokemonData.name)

                    val listTypes = pokemonData.types?.joinToString(separator = ", ") { it.toString()} ?: ""

                    types.setText(listTypes)
                    Glide.with(view)
                        .load(pokemonData.sprites)
                        .apply(requestOptions)
                        .into(image)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(pokemon: String) =
            PokemonDetailFragment().apply {
                this.pokemon =pokemon
            }
    }
}
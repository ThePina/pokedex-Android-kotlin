package com.example.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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
    private lateinit var weight: TextView
    private lateinit var height: TextView

    private lateinit var progressLayout:FrameLayout

    private lateinit var image: ImageView
    private var pokemon: String? = null
    private val pokemonRepository = PokemonRepository()

    //opciones de Glide
    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        name=view.findViewById(R.id.namePokemon)
        image=view.findViewById(R.id.imageView)
        types=view.findViewById(R.id.types)
        weight=view.findViewById(R.id.weight)
        height=view.findViewById(R.id.height)
        progressLayout=view.findViewById(R.id.progressBarLayout)



        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //corrutina para obtener los datos del repositorio sin detener el hilo principal
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val pokemonData = withContext(Dispatchers.IO) {
                    pokemon?.let { pokemonRepository.getPokemonDetails(it) }
                }
                if (pokemonData != null) {
                    name.setText(pokemonData.name)

                    val listTypes = pokemonData.types?.joinToString(separator = ", ") { it.toString()} ?: ""

                    types.setText(listTypes)
                    weight.setText("Weight: "+pokemonData.weight)
                    height.setText("Height: "+pokemonData.height)

                    // setear imagen segun url obtenida desde la api
                    Glide.with(view)
                        .load(pokemonData.sprites)
                        .apply(requestOptions)
                        .into(image)

                    progressLayout.visibility=View.GONE

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
package com.example.pokedex.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.models.PokemonListItemResponse
import com.example.pokedex.ui.fragments.PokemonDetailFragment


class PokemonItemListAdapter(private val context: Context, private var pokemonList: MutableList<PokemonListItemResponse>) :
    RecyclerView.Adapter<PokemonItemListAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtPokemonName: TextView = itemView.findViewById(R.id.textViewName)

        fun bind(pokemon: PokemonListItemResponse) {
            txtPokemonName.text = pokemon.name

            itemView.setOnClickListener{
                val fragmentManager = (itemView.context as AppCompatActivity).supportFragmentManager
                val newFragment = PokemonDetailFragment.newInstance(pokemon.name)

                fragmentManager.beginTransaction()
                    .replace(R.id.frame, newFragment)
                    .addToBackStack(null)
                    .commit()

            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_item_list_view, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon)
    }

    fun updateData(newPokemonList: List<PokemonListItemResponse>) {
        pokemonList.clear()
        pokemonList.addAll(newPokemonList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}

package com.lgvh.gf.pokeapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.lgvh.gf.pokeapp.databinding.FragmentPokemonsBinding
import com.lgvh.gf.pokeapp.home.adapter.PokemonAdapter
import com.lgvh.gf.pokeapp.home.model.Pokemon
import com.lgvh.gf.pokeapp.home.viewmodel.PokemonsViewModel
import com.lgvh.gf.pokeapp.home.viewmodel.PokemonsViewModelFactory
import com.lgvh.gf.pokeapp.roomdb.PokemonDataBase


class PokemonsFragment : Fragment() {

    private lateinit var  viewModel: PokemonsViewModel
    private lateinit var  adapter: PokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonsBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val pokemonDB = PokemonDataBase.getInstance(application).pokemonDBDao
        val viewModelFactory = PokemonsViewModelFactory(pokemonDB, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonsViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val name = this.findNavController().currentBackStackEntry?.arguments?.get("nameSelected")
        if (name != null) {
            viewModel.favSelected(name as String)

        }
        binding.pokemonsRView.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.pokemonsRView.adapter = PokemonAdapter(ArrayList<Pokemon>(),
            PokemonAdapter.OnClickListener {
            viewModel.displayDetail(it)
        })

        viewModel.listPokemon.observe(viewLifecycleOwner, Observer {
            adapter = PokemonAdapter(viewModel.allPokemons, PokemonAdapter.OnClickListener{
                viewModel.displayDetail(it)
            })
            binding.pokemonsRView.adapter = adapter
            //val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            //binding.pokemonsRView.layoutManager = layoutManager
        })


        /*viewModel.notifyAdapter.observe(viewLifecycleOwner, Observer {
            if(it) {
                adapter.notifyDataSetChanged()
                viewModel.resetNotifyAdapter()
            }
        })*/


        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            if ( it != null ) {
                this.findNavController().navigate(
                    PokemonsFragmentDirections.detailPokemon(
                        it.name,
                        it.url
                    )
                )
                viewModel.navigateComplete()
            }
        })

        return  binding.root
    }



}
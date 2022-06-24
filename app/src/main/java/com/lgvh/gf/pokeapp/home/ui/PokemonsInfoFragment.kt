package com.lgvh.gf.pokeapp.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lgvh.gf.pokeapp.R
import com.lgvh.gf.pokeapp.databinding.FragmentPokemonsInfoBinding
import com.lgvh.gf.pokeapp.home.viewmodel.PokemonInfoViewModel
import com.lgvh.gf.pokeapp.home.viewmodel.PokemonsInfoModelFactory


class PokemonsInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonsInfoBinding.inflate(inflater)
        val name = PokemonsInfoFragmentArgs.fromBundle(requireArguments()).name
        val url = PokemonsInfoFragmentArgs.fromBundle(requireArguments()).url
        //Log.i("info pokemon ", name)

        val viewModelFactory = PokemonsInfoModelFactory(name, url)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonInfoViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        activity?.actionBar?.setTitle(R.string.info_title)

        viewModel.openAbilities.observe(viewLifecycleOwner, Observer {
            if(it) {
                //url
                this.findNavController().navigate(
                    PokemonsInfoFragmentDirections.actionPokemonsInfoFragmentToAbilitiyFragment(
                        name,
                        url
                    )
                )
                viewModel.openAbilitiesFinish()
            }

        })

        viewModel.openEvolve.observe(viewLifecycleOwner, Observer {
            if(it) {
                this.findNavController().navigate(
                    PokemonsInfoFragmentDirections.actionPokemonsInfoFragmentToEvolutionaryLineFragment(
                        name,
                        viewModel.infoPokemon.value!!.evolution_chain.url
                    )
                )
                viewModel.openEvolveFinish()
            }
        })

        return binding.root
    }

}
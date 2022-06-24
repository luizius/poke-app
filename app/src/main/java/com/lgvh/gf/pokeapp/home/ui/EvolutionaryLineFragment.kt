package com.lgvh.gf.pokeapp.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.lgvh.gf.pokeapp.home.adapter.EvolveAdapter
import com.lgvh.gf.pokeapp.databinding.FragmentEvolutionaryLineBinding
import com.lgvh.gf.pokeapp.home.viewmodel.EvolutionaryViewModel
import com.lgvh.gf.pokeapp.home.viewmodel.EvolutionaryViewModelFactory


class EvolutionaryLineFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEvolutionaryLineBinding.inflate(inflater)
        val name = EvolutionaryLineFragmentArgs.fromBundle(
            requireArguments()
        ).name
        val url = EvolutionaryLineFragmentArgs.fromBundle(
            requireArguments()
        ).url

        val viewModelFactory = EvolutionaryViewModelFactory(name, url)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EvolutionaryViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.listStringEvolves.observe(viewLifecycleOwner, Observer { it ->
            binding.evolveRView.adapter = EvolveAdapter(it, EvolveAdapter.OnClickListener{
                val navOptions = NavOptions.Builder()
                //navOptions.
                this.findNavController().navigate(
                    EvolutionaryLineFragmentDirections.backToHome(it)
                )
            })
        })

        return binding.root
    }

}
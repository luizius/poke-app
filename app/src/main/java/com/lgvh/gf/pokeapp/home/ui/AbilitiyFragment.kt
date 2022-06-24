package com.lgvh.gf.pokeapp.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lgvh.gf.pokeapp.home.adapter.AbilitiesAdapter
import com.lgvh.gf.pokeapp.databinding.FragmentAbilitiyBinding
import com.lgvh.gf.pokeapp.home.viewmodel.AbilityViewModel
import com.lgvh.gf.pokeapp.home.viewmodel.AbilityViewModelFactory

class AbilitiyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAbilitiyBinding.inflate(inflater)
        val url = AbilitiyFragmentArgs.fromBundle(requireArguments()).url
        val name = AbilitiyFragmentArgs.fromBundle(requireArguments()).name

        val viewModelFactory = AbilityViewModelFactory(name, url)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(AbilityViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.listStringAbilities.observe(viewLifecycleOwner, Observer {
            binding.abilityRView.adapter = AbilitiesAdapter(it)
        })

        return  binding.root
    }



}
package com.example.presentation.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.model.Characters
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDetailCharacterBinding
import com.example.presentation.ui.MainActivity
import com.example.presentation.viewBindingExtensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCharacterFragment : Fragment(R.layout.fragment_detail_character) {

    private val viewModel: DetailCharacterViewModel by viewModel()
    private val binding by viewBinding<FragmentDetailCharacterBinding>()
    private val args: DetailCharacterFragmentArgs by navArgs()

    private lateinit var characters:Characters

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characters = args.character!!

        characters.name?.let { (activity as MainActivity).showToolbar(it, true) }

        setUpView(characters)

    }

    private fun setUpView(characters: Characters){
        Glide.with(requireContext()).load(characters.image).into(binding.imCharacter)
        binding.tvStatus.text = characters.status
        binding.tvlocation.text = characters.location?.name ?: getString(R.string.character_details)
    }


}
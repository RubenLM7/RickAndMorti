package com.example.presentation.ui.list

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.Characters
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCharactersListBinding
import com.example.presentation.ui.MainActivity
import com.example.presentation.ui.common.CharactersUiState
import com.example.presentation.viewBindingExtensions.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment : Fragment(R.layout.fragment_characters_list) {

    private val viewModel: CharactersListViewModel by viewModel()
    private val binding by viewBinding<FragmentCharactersListBinding>()
    private lateinit var adapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).showToolbar(getString(R.string.characters), false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.charactersUiState.collect { renderCharactersUIState(it) }
                }
            }
        }

        createAdapter()
        viewModel.getCharactersList()
    }

    private fun createAdapter() {
        adapter = CharactersAdapter(requireContext(), this::navigateToCharacterDetails)
        binding.rvCharacters.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.rvCharacters.addItemDecoration(DividerItemDecoration(binding.rvCharacters.context, DividerItemDecoration.VERTICAL))
        binding.rvCharacters.adapter = adapter
    }

    private fun navigateToCharacterDetails(characters: Characters) {
        val action = CharactersListFragmentDirections.actionListFragmentToDetailCharacterFragment(characters)
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun renderCharactersUIState(charactersUiState: CharactersUiState){
        when(charactersUiState){
            CharactersUiState.Idle -> {binding.progress.visibility = View.GONE}
            CharactersUiState.Initial -> {binding.progress.visibility = View.VISIBLE}
            is CharactersUiState.OnError -> {
                binding.progress.visibility = View.GONE
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage(getString(R.string.try_again))
                    // if the dialog is cancelable
                    .setNegativeButton(getString(R.string.cancel), DialogInterface.OnClickListener{
                            dialog, id ->
                        dialog.cancel()
                    })
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.ok), DialogInterface.OnClickListener {
                            dialog, id ->
                        viewModel.getCharactersList()
                        dialog.dismiss()

                    })

                val alert = dialogBuilder.create()
                alert.setTitle(getString(R.string.error))
                alert.show()
            }
            is CharactersUiState.OnSuccess -> {
                binding.progress.visibility = View.GONE
                adapter.setCharacters(charactersUiState.result.listCharacters)

            }
            CharactersUiState.Timeout -> {
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage(getString(R.string.timeout_try_again))
                    // if the dialog is cancelable
                    .setNegativeButton(getString(R.string.cancel), DialogInterface.OnClickListener{
                            dialog, id ->
                        dialog.cancel()
                    })
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.ok), DialogInterface.OnClickListener {
                            dialog, id ->
                        viewModel.getCharactersList()
                        dialog.dismiss()

                    })

                val alert = dialogBuilder.create()
                alert.setTitle(getString(R.string.connection_timeout))
                alert.show()
            }
        }
    }

}
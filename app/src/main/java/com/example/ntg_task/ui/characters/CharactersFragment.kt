package com.example.ntg_task.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ntg_task.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.ntg_task.core.base.BaseFragment
import com.example.ntg_task.databinding.FragmentCharactersBinding

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    FragmentCharactersBinding::inflate
) {
    override val viewModel: CharactersViewModel by viewModels()
    private val charactersAdapter = CharactersPagingAdapter {
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentToCharacterProfileFragment(
                it
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharactersRecyclerView()
        collectCharactersData()

    }

    private fun initCharactersRecyclerView() {
        binding.rvCharacters.adapter = charactersAdapter
    }

    private fun collectCharactersData() {
        viewModel.getAllCharacters().observe(viewLifecycleOwner) {
            it?.let {
                charactersAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }

        charactersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
                showLoading(true)
            } else {
                // Hide ProgressBar
                showLoading(false)
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
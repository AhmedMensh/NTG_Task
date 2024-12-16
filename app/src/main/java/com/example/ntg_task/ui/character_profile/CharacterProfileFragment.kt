package com.example.ntg_task.ui.character_profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ntg_task.core.base.BaseFragment
import com.example.ntg_task.databinding.FragmentCharacterProfileBinding
import com.example.ntg_task.domain.entities.MarvelCharacter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterProfileFragment :
    BaseFragment<FragmentCharacterProfileBinding, CharacterProfileViewModel>(
        FragmentCharacterProfileBinding::inflate
    ) {
    override val viewModel: CharacterProfileViewModel by viewModels()
    private val comicsAdapter = ComicsAdapter {
        viewModel.getCharacterComics(it.resourceURI.split("/").last().toInt())
    }
    private val args: CharacterProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComicsRecyclerView()
        bindProfileData(args.profile)
        collectComicsData()
    }

    private fun initComicsRecyclerView(){
        binding.rvComics.adapter = comicsAdapter
    }
    private fun bindProfileData(character: MarvelCharacter) {
        binding.apply {
            Glide.with(requireContext())
                .load(character.thumbnail.path + "." + character.thumbnail.extension)
                .into(ivCharacter)
            tvName.text = character.name
            tvDescription.text = character.description
            tvDescriptionLabel.isVisible = character.description.isNotEmpty()
            tvDescription.isVisible = character.description.isNotEmpty()
            viewModel.allComics.addAll(character.comics)
            comicsAdapter.submitList(viewModel.allComics)
            binding.tvComics.isVisible = character.comics.isNotEmpty()
        }
    }
    private fun collectComicsData() {
        viewModel.comics.observe(viewLifecycleOwner) {
            it?.let { data->
                viewModel.allComics.forEach { c->
                    data.forEach { d->
                        if(c.resourceURI == d.resourceURI){
                            c.thumbnail = d.thumbnail
                    }
                    }
                }
                comicsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun showLoading(show: Boolean) {}
}
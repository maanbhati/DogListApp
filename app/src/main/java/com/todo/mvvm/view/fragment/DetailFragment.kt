package com.todo.mvvm.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.todo.mvvm.data.remote.DogDetailsDomainModel
import com.todo.mvvm.data.remote.Resource
import com.todo.mvvm.databinding.FragmentDogDetailsBinding
import com.todo.mvvm.utils.hasInternetConnection
import com.todo.mvvm.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment() {
    private var binding: FragmentDogDetailsBinding by viewLifecycle()
    private lateinit var detailViewModel: DetailViewModel
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        if (hasInternetConnection(requireContext())) {
            detailViewModel.getDogImage(args.dogName)
            observeImageResponse()
        } else {
            observeOfflineData(args.dogName)
        }
    }

    private fun observeImageResponse() {
        detailViewModel.dogImageResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    shouldShowProgressBar(binding.progressBar, false)
                    it.data?.let { dogDetailsResponse ->
                        bindUi(dogDetailsResponse)
                    }
                }
                is Resource.Error -> {
                    shouldShowProgressBar(binding.progressBar, false)
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        Log.e("DetailFragment", message)
                    }
                }
                is Resource.Loading -> {
                    shouldShowProgressBar(binding.progressBar, true)
                }
            }
        }
    }

    private fun observeOfflineData(dogName: String) {
        detailViewModel.getSavedDogDetail(dogName)
        observeImageResponse()
    }

    private fun bindUi(dogDetailsDomainModel: DogDetailsDomainModel) {
        binding.apply {
            dogImageUrl = dogDetailsDomainModel.dogImageUrl
            dogNameText = dogDetailsDomainModel.dogName.uppercase()
        }
    }
}
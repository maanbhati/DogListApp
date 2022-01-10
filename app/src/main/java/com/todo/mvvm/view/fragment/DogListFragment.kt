package com.todo.mvvm.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.todo.mvvm.R
import com.todo.mvvm.data.remote.Resource
import com.todo.mvvm.databinding.FragmentDogListBinding
import com.todo.mvvm.utils.hasInternetConnection
import com.todo.mvvm.view.adapter.DogListAdapter
import com.todo.mvvm.viewmodel.DogListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogListFragment : BaseFragment(), DogListAdapter.OnItemClickListener {

    private var binding: FragmentDogListBinding by viewLifecycle()
    private lateinit var viewModelDogList: DogListViewModel
    private lateinit var dogListAdapter: DogListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelDogList = ViewModelProvider(this)[DogListViewModel::class.java]
        dogListAdapter = DogListAdapter(this)

        binding.apply {
            rvListItems.setHasFixedSize(true)
            rvListItems.adapter = dogListAdapter
            val itemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            getDrawable(requireContext(), R.drawable.list_divider)?.let {
                itemDecoration.setDrawable(it)
            }
            rvListItems.addItemDecoration(itemDecoration)
        }

        if (hasInternetConnection(requireContext())) {
            viewModelDogList.getDogList()
            observeItemResponse()
        } else {
            observeOfflineData()
        }
    }

    private fun observeItemResponse() {
        viewModelDogList.dogListResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    shouldShowProgressBar(binding.progressBar, false)
                    it.data?.let { itemResponse ->
                        dogListAdapter.submitList(itemResponse.dogList)
                    }
                }
                is Resource.Error -> {
                    shouldShowProgressBar(binding.progressBar, false)
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        Log.e("DogListFragment", message)
                    }
                }
                is Resource.Loading -> {
                    shouldShowProgressBar(binding.progressBar, true)
                }
            }
        }
    }

    private fun observeOfflineData() {
        viewModelDogList.getSavedDogList()
        observeItemResponse()
    }

    override fun onItemClick(breed: String) {
        val action = DogListFragmentDirections.actionListFragmentToDetailFragment(breed)
        findNavController().navigate(action)
    }
}
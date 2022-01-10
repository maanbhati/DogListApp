package com.todo.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.mvvm.data.helper.ModelConverter
import com.todo.mvvm.data.local.model.DogListEntity
import com.todo.mvvm.data.remote.DogListDomainModel
import com.todo.mvvm.data.remote.Resource
import com.todo.mvvm.repository.DogListRepository
import com.todo.networkmodule.dto.DogListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val dogListRepository: DogListRepository,
    private val modelConverter: ModelConverter
) :
    ViewModel() {
    private val _dogListResponse = MutableLiveData<Resource<DogListDomainModel>>()
    val dogListResponse: MutableLiveData<Resource<DogListDomainModel>>
        get() = _dogListResponse

    fun getDogList() = viewModelScope.launch(Dispatchers.IO) {
        safeDogListCall()
    }

    private suspend fun safeDogListCall() {
        _dogListResponse.postValue(Resource.Loading())
        try {
            val response = dogListRepository.getDogList()
            _dogListResponse.postValue(handleDogListResponse(response))
        } catch (error: Exception) {
            when (error) {
                is IOException -> _dogListResponse.postValue(Resource.Error("Network failure"))
                else -> _dogListResponse.postValue(Resource.Error("Error in data conversion"))
            }
        }
    }

    private fun handleDogListResponse(response: Response<DogListResponse>): Resource<DogListDomainModel> {
        if (response.isSuccessful) {
            response.body()?.let { dogList ->
                val entityModel = modelConverter.convertFromDtoToEntityModel(dogList.message)
                val domainModel = modelConverter.convertEntityToDomainModel(entityModel)
                updateDogListData(entityModel)
                return Resource.Success(domainModel)
            }
        }
        return Resource.Error(response.message())
    }

    fun updateDogListData(dogListEntity: DogListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dogListRepository.deleteAllData()
                dogListRepository.insertDogList(dogListEntity)
            } catch (error: Exception) {
                _dogListResponse.postValue(Resource.Error("Error in data saving"))
            }
        }
    }

    fun getSavedDogList() {
        viewModelScope.launch(Dispatchers.IO) {
            val dogListEntityModel = dogListRepository.getSavedDogList()
            val dogListDomainModel =
                modelConverter.convertEntityToDomainModel(dogListEntityModel)
            _dogListResponse.postValue(Resource.Success(dogListDomainModel))
        }
    }
}
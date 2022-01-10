package com.todo.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.mvvm.data.helper.ModelConverter
import com.todo.mvvm.data.local.model.DogDetailsEntity
import com.todo.mvvm.data.remote.DogDetailsDomainModel
import com.todo.mvvm.data.remote.Resource
import com.todo.mvvm.repository.DogListRepository
import com.todo.mvvm.utils.HYPHEN
import com.todo.networkmodule.dto.DogImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dogListRepository: DogListRepository,
    private val modelConverter: ModelConverter
) : ViewModel() {
    private val _dogImageResponse = MutableLiveData<Resource<DogDetailsDomainModel>>()
    val dogImageResponse: MutableLiveData<Resource<DogDetailsDomainModel>>
        get() = _dogImageResponse

    fun getDogImage(breedName: String) = viewModelScope.launch(Dispatchers.IO) {
        safeDogImageCall(breedName.substringAfter(HYPHEN))
    }

    private suspend fun safeDogImageCall(breedName: String) {
        _dogImageResponse.postValue(Resource.Loading())
        try {
            val response = dogListRepository.getDogImage(breedName)
            _dogImageResponse.postValue(handleDogImageResponse(response, breedName))
        } catch (error: Exception) {
            when (error) {
                is IOException -> _dogImageResponse.postValue(Resource.Error("Network failure"))
                else -> _dogImageResponse.postValue(Resource.Error("Error in data conversion"))
            }
        }
    }

    private fun handleDogImageResponse(
        response: Response<DogImageResponse>,
        breedName: String
    ): Resource<DogDetailsDomainModel> {
        if (response.isSuccessful) {
            response.body()?.let { dogImageResponse ->
                val dogDetailsEntity =
                    modelConverter.convertFromDtoToDogDetailsEntity(
                        dogImageResponse.message,
                        breedName
                    )
                val dogDetailsDomainModel =
                    modelConverter.convertDogDetailsEntityToDomainModel(dogDetailsEntity)
                updateDogDetailData(dogDetailsEntity)
                return Resource.Success(dogDetailsDomainModel)
            }
        }
        return Resource.Error(response.message())
    }

    fun updateDogDetailData(dogDetailsEntity: DogDetailsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dogListRepository.deleteDogDetail(dogDetailsEntity.dogName)
                dogListRepository.insertDogDetail(dogDetailsEntity)
            } catch (error: Exception) {
                _dogImageResponse.postValue(Resource.Error("Error in data saving"))
            }
        }
    }

    fun getSavedDogDetail(dogName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dogDetailEntityModel = dogListRepository.getSavedDogDetail(dogName)
            val dogDetailDomainModel =
                modelConverter.convertDogDetailsEntityToDomainModel(dogDetailEntityModel)
            _dogImageResponse.postValue(Resource.Success(dogDetailDomainModel))
        }
    }
}
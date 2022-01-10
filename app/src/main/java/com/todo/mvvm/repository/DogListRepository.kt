package com.todo.mvvm.repository

import com.todo.mvvm.data.local.DogListDao
import com.todo.mvvm.data.local.model.DogDetailsEntity
import com.todo.mvvm.data.local.model.DogListEntity
import com.todo.networkmodule.api.Api
import com.todo.networkmodule.dto.DogImageResponse
import com.todo.networkmodule.dto.DogListResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogListRepository @Inject constructor(
    private val api: Api,
    private val dogListDao: DogListDao
) {
    suspend fun getDogList(): Response<DogListResponse> = api.getDogList()

    suspend fun getDogImage(breedName: String): Response<DogImageResponse> =
        api.getDogImage(breedName)

    fun getSavedDogList() = dogListDao.getSavedDogList()

    suspend fun insertDogList(dogListEntity: DogListEntity) =
        dogListDao.insert(dogListEntity)

    suspend fun deleteAllData() = dogListDao.deleteAll()

    fun getSavedDogDetail(dogName: String) = dogListDao.getSavedDogDetail(dogName)

    suspend fun insertDogDetail(dogDetailsEntity: DogDetailsEntity) =
        dogListDao.insertDogDetail(dogDetailsEntity)

    suspend fun deleteDogDetail(dogName: String) = dogListDao.deleteDogDetail(dogName)
}
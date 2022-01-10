package com.todo.mvvm.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.todo.mvvm.data.local.model.DogDetailsEntity
import com.todo.mvvm.data.local.model.DogListEntity
import com.todo.mvvm.utils.TABLE_DOG_DETAIL
import com.todo.mvvm.utils.TABLE_NAME

@Dao
interface DogListDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getSavedDogList(): DogListEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogListEntity: DogListEntity): Long

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogDetail(dogDetailsEntity: DogDetailsEntity): Long

    @Query("SELECT * FROM $TABLE_DOG_DETAIL WHERE dogName == :dogName")
    fun getSavedDogDetail(dogName: String): DogDetailsEntity

    @Query("DELETE FROM $TABLE_DOG_DETAIL WHERE dogName == :dogName")
    suspend fun deleteDogDetail(dogName: String)
}
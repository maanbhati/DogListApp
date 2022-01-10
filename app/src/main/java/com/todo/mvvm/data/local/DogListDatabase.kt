package com.todo.mvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.todo.app.data.local.Converters
import com.todo.mvvm.data.local.model.DogDetailsEntity
import com.todo.mvvm.data.local.model.DogListEntity
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [DogListEntity::class, DogDetailsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DogListDatabase : RoomDatabase() {
    abstract fun getDogListDao(): DogListDao

    class Callback @Inject constructor(
        private val database: Provider<DogListDatabase>
    ) : RoomDatabase.Callback()
}
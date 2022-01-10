package com.todo.mvvm.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.todo.mvvm.utils.TABLE_DOG_DETAIL

@Entity(
    tableName = TABLE_DOG_DETAIL,
    indices = [Index(value = ["dogImageUrl", "id"], unique = true)]
)
data class DogDetailsEntity(
    @ColumnInfo(name = "dogImageUrl")
    val dogImageUrl: String,
    @ColumnInfo(name = "dogName")
    val dogName: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0
)
package com.todo.mvvm.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.todo.mvvm.utils.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [Index(value = ["dogList", "id"], unique = true)]
)
data class DogListEntity(
    @ColumnInfo(name = "dogList")
    val dogList: List<String>,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0
)

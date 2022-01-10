package com.todo.mvvm.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.todo.mvvm.databinding.ActivityDogListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityDogListBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }
}
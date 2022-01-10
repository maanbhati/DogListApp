package com.todo.mvvm.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("dogImage")
    fun loadImage(view: AppCompatImageView, url: String?) = view.loadImage(url)

    @JvmStatic
    @BindingAdapter("dogName")
    fun loadName(view: AppCompatTextView, dogName: String?) {
        view.text = dogName
    }
}
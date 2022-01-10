package com.todo.mvvm.view.fragment

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun shouldShowProgressBar(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
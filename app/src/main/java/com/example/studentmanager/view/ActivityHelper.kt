package com.example.studentmanager.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.provider.Settings.Global.getString
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R

class ActivityHelper {
    fun closeKeyboard(activity: Activity?){
        val view = activity?.currentFocus

        if(view != null){
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
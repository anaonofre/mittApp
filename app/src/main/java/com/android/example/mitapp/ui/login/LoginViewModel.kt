package com.android.example.mitapp.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.mitapp.model.User
import com.google.android.material.textfield.TextInputEditText

class LoginViewModel : ViewModel() {



    fun validText(txt: String):Boolean{
        if (txt.length!=6 || txt.isEmpty()){
            return false
        }

        return true
    }













}
package com.example.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.testapp.model.UserModel
import com.example.testapp.repository.Repository

class MainViewModel : ViewModel() {

    private val _userId: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<UserModel> = Transformations
        .switchMap(_userId){
            Repository.getUser(it)
        }

    fun setUserId(userId: String){
        val update = userId
        if(_userId.value == update){
            return
        }
        _userId.value = update
    }
    fun cancelJobs(){
        Repository.cancelJobs()
    }

}
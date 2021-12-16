package com.example.testapp.repository

import androidx.lifecycle.LiveData
import com.example.testapp.api.RetroitBuilder
import com.example.testapp.model.UserModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<UserModel> {
        job = Job()
        return object:LiveData<UserModel>(){
            override fun onActive() {
                super.onActive()
                job?.let{thisJob ->
                    CoroutineScope(IO+thisJob).launch {
                        val user = RetroitBuilder.apiService.getUser(userId)
                        withContext(Main){
                            value = user
                            thisJob.complete()
                        }
                    }

                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }
}
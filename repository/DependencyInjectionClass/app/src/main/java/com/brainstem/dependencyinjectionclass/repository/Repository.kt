package com.brainstem.dependencyinjectionclass.repository

import com.brainstem.dependencyinjectionclass.api.ApiUsers
import com.brainstem.dependencyinjectionclass.model.UsersItem
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiUsers: ApiUsers){

    suspend fun getUsers(): Response<ArrayList<UsersItem>> {
        return apiUsers.getUsers()
    }
}
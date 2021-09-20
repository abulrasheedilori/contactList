package com.brainstem.dependencyinjectionclass.api

import android.graphics.PostProcessor
import com.brainstem.dependencyinjectionclass.model.Users
import com.brainstem.dependencyinjectionclass.model.UsersItem
import org.w3c.dom.Comment
import retrofit2.Response
import retrofit2.http.GET

interface ApiUsers {

    @GET("users")
    suspend fun getUsers(): Response<ArrayList<UsersItem>>
}

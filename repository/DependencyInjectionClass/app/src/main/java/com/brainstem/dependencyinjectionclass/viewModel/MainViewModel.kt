package com.brainstem.dependencyinjectionclass.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainstem.dependencyinjectionclass.model.Users
import com.brainstem.dependencyinjectionclass.model.UsersItem
import com.brainstem.dependencyinjectionclass.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.w3c.dom.Comment
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val repository: Repository): ViewModel() {

    var usersResponse: MutableLiveData<Response<ArrayList<UsersItem>>> = MutableLiveData()

    fun getUsers(){
        viewModelScope.launch {
            usersResponse.value = repository.getUsers()
        }
    }
}
package com.everest.pagerlistdrawer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everest.pagerlistdrawer.network.model.UserRepository
import com.everest.pagerlistdrawer.network.model.UsersAPI
import com.everest.pagerlistdrawer.ui.User
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch

sealed class UIState {
    object Loading : UIState()
    class Data(val data: List<User>) : UIState()
    class Error(val message: String) : UIState()
}

class CustomViewModel : ViewModel() {

    val retrofit = RetrofitClient.getClient()
    val usersAPI = retrofit.create(UsersAPI::class.java)

    val userRepository = UserRepository(usersAPI)

    private val _uiState = MutableLiveData<UIState>(UIState.Loading)
    val uiState: LiveData<UIState> = _uiState

    init {
        viewModelScope.launch(Default) {
            _uiState.postValue(UIState.Loading)
            try {
                val users = userRepository.getUsers()
                _uiState.postValue(UIState.Data(users)gs)
            } catch (e: Exception) {
                _uiState.postValue(UIState.Error(e.localizedMessage))
            }
        }
    }
}
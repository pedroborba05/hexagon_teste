//package com.example.hexagon_tecnico.presentation.inactiveusers
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.asLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.hexagon_tecnico.domain.model.User
//import com.example.hexagon_tecnico.domain.repository.UserRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
//@HiltViewModel
//class InactiveUsersViewModel @Inject constructor(
//    private val repo: UserRepository
//): ViewModel() {
//    val inactiveUsers: LiveData<List<User>> = repo.getInactiveUsersFromRoom().asLiveData(viewModelScope.coroutineContext)
//
//    fun activeUser(user:)
//}
package br.com.zup.movieflix.register.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.repository.RegisterRepository

class RegisterViewModel {
    private val repository = RegisterRepository
    private val _response = MutableLiveData<Boolean>()
    val usuarioCadastrado: LiveData<Boolean> = _response

    fun registerUser(register: RegisterModel){
        try {
            repository.addLogin(register)
            _response.value = true
        }catch (e: Exception){
            _response.value = false
            Log.d("Error", "---> ${e.message}")
        }
    }
}
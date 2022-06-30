package br.com.zup.movieflix.register.repository

import br.com.zup.movieflix.register.model.RegisterModel
import java.lang.Exception

object RegisterRepository {
    private val loginRepository = mutableListOf<RegisterModel>()

    fun addLogin(register: RegisterModel){
        val index = loginRepository.indexOf(register)
        if(index < 0){
            loginRepository.add(register)
        }else{
            throw Exception("Usuário já registrado")
        }
    }
}
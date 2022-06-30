package br.com.zup.movieflix.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.zup.movieflix.databinding.ActivityRegisterBinding
import br.com.zup.movieflix.login.view.LoginActivity
import br.com.zup.movieflix.register.ConstRegister
import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by lazy {
        RegisterViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickRegister()
        observes()

    }

    private fun clickRegister(){
        binding.bvLogin.setOnClickListener {
            val register = setRegisterInformation()
            register?.let {
                viewModel.registerUser(register)
            }
        }
    }

    private fun observes(){
        viewModel.usuarioCadastrado.observe(this){
            if(it){
                Toast.makeText(this, "Usuário cadastrado", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }else{
                Toast.makeText(this, "Usuário já existe", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setRegisterInformation(): RegisterModel? {
        val name = binding.etUserNameRegister.text.toString()
        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()
        val confirmPassword = binding.etConfirmPasswordRegister.text.toString()

        if(verifyFields(name, email, password, confirmPassword)){
            if (verifyPassword(password, confirmPassword)){
                cleanFields()
                return RegisterModel(name, email, password)
            }else{
                messagePasswordFail()
            }
        }
        return null
    }

    private fun verifyFields(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
    }

    private fun verifyPassword(password: String, confirmPassword: String): Boolean{
        return password == confirmPassword
    }

    private fun messagePasswordFail(){
        binding.etConfirmPasswordRegister.error = ConstRegister.MESSAGE_PASSWORD_FAIL
    }

    private fun cleanFields(){
        binding.etUserNameRegister.text.clear()
        binding.etEmailRegister.text.clear()
        binding.etPasswordRegister.text.clear()
        binding.etConfirmPasswordRegister.text.clear()
    }
}
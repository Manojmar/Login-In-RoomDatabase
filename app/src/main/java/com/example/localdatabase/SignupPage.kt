package com.example.localdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.localdatabase.databinding.ActivitySignupPageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class SignupPage : AppCompatActivity() {
    private val signupPageBinding by lazy {
        ActivitySignupPageBinding.inflate(layoutInflater)
    }
    var userName : String =""
    var emailInput: String =""
    var passwordInput: String =""
    var confirmPassword: String =""
    private val passwordPattern: Pattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(signupPageBinding.root)
        signupPageBinding.btnRegister.setOnClickListener{
            validateName()
            validateEmail()
            validatePassword()
            confirmPassword()
            saveUserDetail()

        }
        signupPageBinding.alreadyMember.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserDetail() {
        val name = signupPageBinding.signUpName.text.toString()
        val email = signupPageBinding.signUpEmail.text.toString()
        val pwd = signupPageBinding.signUpPassword.text.toString()
        val database: MyDatabase? = MyDatabase.getInstance(this)
        val userDatabaseAccessObject: UserDao? = database?.Usereddao()
//        val userEntity = userDatabaseAccessObject?.insert(User(0,name,email,pwd))
        val user = User(0, name, email, pwd)
        GlobalScope.launch {
            userDatabaseAccessObject?.insert(user)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@SignupPage, "Register Success", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun validateName():Boolean {
     userName = signupPageBinding.signUpName.text.toString()
        return if (userName.isEmpty()){
            signupPageBinding.signUpNameContainer.error ="Field Can't  be empty"
            false
        }else{
            signupPageBinding.signUpNameContainer.error = null
            true
        }

    }

    private fun validateEmail(): Boolean {
        // Extract input from EditText
         emailInput = signupPageBinding.signUpEmail .text.toString()

        // if the email input field is empty
        return if (emailInput.isEmpty()) {
            signupPageBinding.signUpEmailContainer.error = "Field can not be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            signupPageBinding.signUpEmailContainer.error = "Please enter a valid email address"
            false
        } else {
            signupPageBinding.signUpEmailContainer.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
       passwordInput = signupPageBinding.signUpPassword.text.toString().trim()
        // if password field is empty
        // it will display error message "Field can not be empty"
        return if (passwordInput.isEmpty()) {
            signupPageBinding.signUpPasswordContainer.error = "Field can not be empty"
            false
        } else if (!passwordPattern.matcher(passwordInput).matches()) {
            signupPageBinding.signUpPasswordContainer.error = "Password is too weak"
            false
        } else {
            signupPageBinding.signUpPasswordContainer.error = null
            true
        }
    }

    private fun confirmPassword():Any{
        passwordInput = signupPageBinding.signUpPassword.text.toString().trim()
         confirmPassword = signupPageBinding.signUpConfirmPwd.text.toString().trim()
        return if (confirmPassword.isEmpty()){
            signupPageBinding.signUpConfirmPwdContainer.error = "Field can not be empty"
            false
        }else if (passwordInput != confirmPassword){
            signupPageBinding.signUpConfirmPwdContainer.error = "Password Not matching"
            false
        } else{
            signupPageBinding.signUpConfirmPwdContainer.error = null
        }

    }

}
package com.example.localdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.localdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var userName:String=""
    var password:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        mainBinding.btnLogin.setOnClickListener{
         userName = mainBinding.loginEmail.text.toString()
            password = mainBinding.loginPassword.text.toString()
            if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show()
            }
              else{
                findData()

                }
        }
        mainBinding.signupRegister.setOnClickListener {
            val intent = Intent(this,SignupPage::class.java)
            startActivity(intent)
        }
    }

    private fun findData() {
        val dataBase= MyDatabase.getInstance(applicationContext)
        val noteDao=dataBase!!.Usereddao()
        CoroutineScope(Dispatchers.IO).launch {
        val list=noteDao.getAllUser()
            withContext(Dispatchers.Main){

                for (i in list.indices){
                    if (userName==list[i].email && password==list[i].password){
                        val intent = Intent(this@MainActivity, HomeScreen::class.java)
                        intent.putExtra("user", list[i].name)
                        startActivity(intent)
                    }else {

                    }
                }
            }
        }

    }
}
package com.example.localdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.localdatabase.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {
    private val homeScreenBinding by lazy {
        ActivityHomeScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homeScreenBinding.root)
        val userName = intent.getStringExtra("user")
        homeScreenBinding.userAccountName.text = userName
    }
}
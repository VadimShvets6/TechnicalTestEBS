package com.top1shvetsvadim1.testebs.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.top1shvetsvadim1.testebs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    companion object{

    }
}
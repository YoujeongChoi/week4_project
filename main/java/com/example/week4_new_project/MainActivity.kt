
package com.example.week4_new_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.week4_new_project.databinding.ActivityLoginBinding
import com.example.week4_new_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    private lateinit var mBinding: ActivityMainBinding
    private val binding get() = mBinding!!



    lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "MainActivity - onCreate() called")

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        super.onCreate(savedInstanceState)
        setContentView(view)


        binding.startLoginBtn.setOnClickListener {
            Log.d(TAG, "MainActivity - onCreate() called 버튼")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.startStartBtn.setOnClickListener{
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }

        if (intent.hasExtra("user_name")) {
            val user_name = binding.startUserName
            user_name.text = intent.getStringExtra("user_name")
            username = user_name.toString()
        } else {
            username = "No Name"
        }

    }

    override fun onRestart() {
        super.onRestart()

        if (intent.hasExtra("user_name")) {
            val user_name = binding.startUserName
            user_name.text = intent.getStringExtra("user_name")
            username = user_name.toString()
        } else {
            username = "No Name"
        }
    }
}
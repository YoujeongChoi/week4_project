package com.example.week4_new_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.week4_new_project.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLoginBinding
    private val binding get() = mBinding!!

    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "LoginActivity - onCreate() called")
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        super.onCreate(savedInstanceState)
        setContentView(view)

        data class UserData (
            var name: String = "",
            var id: String="",
            var pw: String=""
        )


        binding.loginBtn.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            val login_name_et = binding.loginNameEt
            val login_id_et = binding.loginIdEt
            val login_pw_et = binding.loginPwEt
            intent.putExtra("user_name", login_name_et.text.toString())
            intent.putExtra("user_id", login_id_et.text.toString())
            intent.putExtra("user_pw", login_pw_et.text.toString())

            var user_info = mutableListOf<UserData>()
            user_info.clear()

            var user = UserData()
            user.name = binding.loginNameEt.toString()
            user.id = binding.loginIdEt.toString()
            user.pw = binding.loginPwEt.toString()

            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()



        }

    }

}
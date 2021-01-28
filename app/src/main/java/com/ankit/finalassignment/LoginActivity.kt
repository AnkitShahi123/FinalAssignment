package com.ankit.finalassignmentproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ankit.finalassignment.R
import com.ankit.finalassignmentproject.db.UserDB
import com.ankit.finalassignmentproject.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity(){
    private lateinit var etLoginUsername: EditText
    private lateinit var etLoginPasswd: EditText
    private lateinit var btnLogin: Button
    private lateinit var goToReg: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etLoginUsername = findViewById(R.id.etLoginUsername)
        etLoginPasswd = findViewById(R.id.etLoginPasswd)
        btnLogin = findViewById(R.id.btnLogin)
        goToReg = findViewById(R.id.goToReg)

        btnLogin.setOnClickListener{
            login()
        }

        goToReg.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun login() {
        val username = etLoginUsername.text.toString()
        val password = etLoginPasswd.text.toString()

        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = UserDB
                .getInstance(this@LoginActivity)
                .getuserDAO()
                .checkUser(username, password)
            if (user == null) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                startActivity(
                    Intent(
                        this@LoginActivity,
                        MainActivity::class.java
                    )
                )
            }
        }
    }



}
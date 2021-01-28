package com.ankit.finalassignmentproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etRegUser : EditText
    private lateinit var etPasswd : EditText
    private lateinit var etConfirmPasswd : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPhone : EditText
    private lateinit var btnReg : Button
    private lateinit var goToLogin : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etRegUser = findViewById(R.id.etRegUser)
        etPasswd = findViewById(R.id.etPasswd)
        etConfirmPasswd = findViewById(R.id.etConfirmPasswd)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        btnReg = findViewById(R.id.btnRegister)
        goToLogin = findViewById(R.id.goToLogin)

        btnReg.setOnClickListener {
            val username = etRegUser.text.toString()
            val password = etPasswd.text.toString()
            val confrimPassword = etConfirmPasswd.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()


            val user = User(username, password, email, phone)
            CoroutineScope(Dispatchers.IO).launch {

                UserDB.getInstance(this@RegisterActivity).getuserDAO().registerUser(user)
            }
            Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()

        }

        goToLogin.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}
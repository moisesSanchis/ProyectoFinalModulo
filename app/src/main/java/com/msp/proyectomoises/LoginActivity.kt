package com.msp.proyectomoises

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import entities.User

class LoginActivity : AppCompatActivity(){
    private lateinit var etUserLogin:EditText
    private lateinit var etPasswordLogin:EditText
    private lateinit var btnLogin:Button
    private lateinit var btnRegister:Button
    private lateinit var  btnExit:ImageButton
    private lateinit var user:User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUserLogin = findViewById(R.id.etUserLogin)
        etPasswordLogin = findViewById(R.id.etPasswordLogin)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegisterLogin)
        btnExit = findViewById(R.id.ibtnExitLogin)
        var userString:String =""
        var pass:String =""
        etUserLogin.addTextChangedListener{
          userString = etUserLogin.text.toString()
        }
        etPasswordLogin.addTextChangedListener{
            pass =  etPasswordLogin.text.toString()
        }

        btnLogin.setOnClickListener {
            if (userString == "moises" && pass == "1234"){
                user = User("Moises", "1234",20, "moises@gmail.com")
                val intentMain = Intent(this, MainActivity::class.java)
                intentMain.putExtra("newUser", user)
                startActivity(intentMain)


            }else{
                Toast.makeText(this, R.string.TxtErrorLogin, Toast.LENGTH_SHORT).show()
            }

        }
        btnRegister.setOnClickListener {
            val intentRegister = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intentRegister)
        }
        btnExit.setOnClickListener {
            finishAffinity()
        }



    }
}
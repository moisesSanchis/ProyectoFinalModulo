package com.msp.proyectomoises


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import entities.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var etUserName: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var etUserRepeatPassword: EditText
    private lateinit var etUserMail: EditText
    private lateinit var btnRegister:Button
    lateinit var newUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var userName:String = ""
        var userPassword:String = ""
        var userRepeatPassWord:String = ""
        var userMail:String = ""
        etUserName.addTextChangedListener{
            userName = etUserName.text.toString()
        }
        etUserPassword.addTextChangedListener{
            userPassword = etUserPassword.text.toString()
        }
        etUserRepeatPassword.addTextChangedListener{
            userRepeatPassWord = etUserRepeatPassword.text.toString()
        }
        etUserMail.addTextChangedListener{
            userMail = etUserMail.text.toString()
        }

        btnRegister.setOnClickListener {
            if(userPassword==userRepeatPassWord){
                newUser =  User(userName, userPassword,0, userMail)
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                intent.putExtra("newUser", newUser)
                startActivity(intent)
            }else{
                Toast.makeText(this, R.string.TxtErrorPassword, Toast.LENGTH_SHORT).show()

            }
        }
    }
}
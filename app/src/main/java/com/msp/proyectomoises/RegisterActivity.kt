package com.msp.proyectomoises


import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import database.RecyclerDao
import database.RecyclerSQLiteHelper
import entities.User
import java.util.Random

class RegisterActivity : AppCompatActivity() {
    private lateinit var etUserName: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var etUserRepeatPassword: EditText
    private lateinit var etUserMail: EditText
    private lateinit var btnRegister: Button
    lateinit var newUser: User
    lateinit var recyclerDB: RecyclerSQLiteHelper

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var userName: String = ""
        var userPassword: String = ""
        var userRepeatPassWord: String = ""
        var userMail: String = ""
        var nameExist = false
        recyclerDB = RecyclerSQLiteHelper(this)
        etUserName = findViewById(R.id.etNameUserRegister)
        etUserPassword = findViewById(R.id.etPasswordRegister)
        etUserRepeatPassword = findViewById(R.id.etRepeatPasswordRegister)
        etUserMail = findViewById(R.id.etEmailRegister)
        btnRegister = findViewById(R.id.btnRegisterRegister)
        etUserName.addTextChangedListener {
            userName = etUserName.text.toString()
        }
        etUserPassword.addTextChangedListener {
            userPassword = etUserPassword.text.toString()
        }
        etUserRepeatPassword.addTextChangedListener {
            userRepeatPassWord = etUserRepeatPassword.text.toString()
        }
        etUserMail.addTextChangedListener {
            userMail = etUserMail.text.toString()
        }

        btnRegister.setOnClickListener {
            val readerDb: SQLiteDatabase = recyclerDB.readableDatabase
            val cursor = readerDb.rawQuery("SELECT name FROM user", null)
            val listNames = ArrayList<String>()


            if (cursor.moveToFirst()) {
                do {
                    listNames.add(cursor.getString(0))
                } while (cursor.moveToNext())
            }

            if (userPassword == userRepeatPassWord) {

               listNames.forEach{ name ->

                   if (name == userName){
                      nameExist = true
                   }
               }
                if (!nameExist){
                    val imagenesPerfil = arrayListOf<Int>(2131231009,2131231010, 2131231011, 2131231012)
                    val randomIndex = Random().nextInt(imagenesPerfil.size)
                    val randomProfile = imagenesPerfil[randomIndex]
                    newUser = User(userName, userPassword, 0, userMail, randomProfile)
                    if (newUser.getName() != null && newUser.getPassword() != null && newUser.getMail() != null) {
                        recyclerDB.setUser(newUser)
                        Toast.makeText(this, R.string.TxtUserSaveDataBase, Toast.LENGTH_LONG).show()
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        intent.putExtra("newUser", newUser)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, R.string.TxtUserErrorDataBase, Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, R.string.TxtNameExists, Toast.LENGTH_LONG).show()
                    etUserName.setText("")

                }



            } else {
                Toast.makeText(this, R.string.TxtErrorPassword, Toast.LENGTH_SHORT).show()

            }
        }
    }
}
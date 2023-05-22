package com.msp.proyectomoises

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import database.RecyclerSQLiteHelper
import entities.User
import preferences.ProjectApplication.Companion.prefs

class LoginActivity : AppCompatActivity() {
    private lateinit var etUserLogin: EditText
    private lateinit var etPasswordLogin: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    lateinit var recyclerDB: RecyclerSQLiteHelper

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        recyclerDB = RecyclerSQLiteHelper(this)
        etUserLogin = findViewById(R.id.etUserLogin)
        etPasswordLogin = findViewById(R.id.etPasswordLogin)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegisterLogin)
        var userString: String = ""
        var pass: String = ""
        etUserLogin.addTextChangedListener {
            userString = etUserLogin.text.toString()
        }
        etPasswordLogin.addTextChangedListener {
            pass = etPasswordLogin.text.toString()
        }

        btnLogin.setOnClickListener {
            val readerDb: SQLiteDatabase = recyclerDB.readableDatabase
            val cursor = readerDb.rawQuery("SELECT name, password,points, mail, image  FROM user", null)
            val listUsers = ArrayList<User>()
            var count = 0
            if (cursor.moveToFirst()) {
                do {
                    val nameCursor: String = cursor.getString(0)
                    val passwordCursor: String = cursor.getString(1)
                    val mailCursor: String = cursor.getString(3)
                    val pointsCursor: Int = cursor.getInt(2)
                    val imageCursor: Int = cursor.getInt(4)
                    val userActual = User(nameCursor, passwordCursor, pointsCursor, mailCursor, imageCursor)
                    listUsers.add(userActual)
                } while (cursor.moveToNext())
            }
            listUsers.forEach{ user ->

                if (userString == user.getName() && pass == user.getPassword()) {
                    user.getName()
                        ?.let {
                            user.getPoints()?.let { it1 ->
                                prefs.saveData(
                                    it,
                                    it1, user.getMail()!!, user.getImage()!!
                                )
                            }
                        }
                    val intentMain = Intent(this, MainActivity::class.java)
                  //  intentMain.putExtra("newUser", user)
                    startActivity(intentMain)


                } else {
                    count++
                }
            }
            if (listUsers.size==count){
                Toast.makeText(this, R.string.TxtErrorLogin, Toast.LENGTH_SHORT).show()

            }


        }
        btnRegister.setOnClickListener {
            val intentRegister = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intentRegister)
        }



    }
}
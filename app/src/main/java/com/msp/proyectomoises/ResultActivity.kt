package com.msp.proyectomoises

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import database.RecyclerSQLiteHelper
import entities.Container
import entities.User
import preferences.ProjectApplication

class ResultActivity : AppCompatActivity() {
    lateinit var recyclerDB: RecyclerSQLiteHelper
    lateinit var ivresult: ImageView
    lateinit var tvResult: TextView



    @SuppressLint("MissingInflatedId", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        ivresult = findViewById(R.id.ivResults)
        tvResult = findViewById(R.id.tvResults)
        recyclerDB = RecyclerSQLiteHelper(this)
        val codeContainer = intent.getStringExtra("codeBar")
        val readerDb: SQLiteDatabase = recyclerDB.readableDatabase
        val cursor = readerDb.rawQuery("SELECT *, type FROM container", null)
        val listContainers = ArrayList<Container>()
        var containerScanned = Container()

        if (cursor.moveToFirst()) {
            do {
                val codeCursor: String = cursor.getString(0)
                val typeCursor: String = cursor.getString(1)
                val recycledCursor:String = cursor.getString(2)
                val containerActual = Container(codeCursor, typeCursor,recycledCursor)
                listContainers.add(containerActual)
            } while (cursor.moveToNext())
        }


        listContainers.forEach { container ->


            if (codeContainer == container.getCode().toString()) {
                containerScanned = container


            }
        }

        if (!containerScanned.getCode().equals("")){

            when(containerScanned.getType()){
                "plastic" ->{
                    ivresult.setImageResource(R.drawable.container_plastic)
                    tvResult.setText(R.string.TxtPlasticResult)
                    if (codeContainer != null) {
                        sumPoints(containerScanned)
                    }
                }
                "glass" ->{
                    ivresult.setImageResource(R.drawable.container_glass)
                    tvResult.setText(R.string.TxtGlassResult)
                    if (codeContainer != null) {
                        sumPoints(containerScanned)
                    }
                }
                "cardboard" ->{
                    ivresult.setImageResource(R.drawable.container_cardboard)
                    tvResult.setText(R.string.TxtCardboardResult)
                    if (codeContainer != null) {
                        sumPoints(containerScanned)
                    }

                }
                "electronic" ->{
                    ivresult.setImageResource(R.drawable.ecopark)
                    tvResult.setText(R.string.TxtEcoparkResult)
                    if (codeContainer != null) {
                        sumPoints(containerScanned)
                    }
                }

            }

        }
        val navigationView: BottomNavigationView = findViewById(R.id.menuNavigationResult)
        navigationView.selectedItemId = R.id.navigationHome
        navigationView.setOnItemSelectedListener { menuItem ->
            // Código a ejecutar cuando se seleccione un elemento del menú
            when (menuItem.itemId) {
                R.id.navigationHome -> {
                    // Código a ejecutar cuando se selecciona el elemento Home
                      val intentMain = Intent(this, MainActivity::class.java)
                      startActivity(intentMain)

                }

                R.id.navigationLearn -> {
                    // Código a ejecutar cuando se selecciona el elemento search
                    val intentLearn = Intent(this, LearnerActivity::class.java)
                    startActivity(intentLearn)

                }

                R.id.navigationCalculator -> {
                    // Código a ejecutar cuando se selecciona el elemento calculator
                    val intencalculator = Intent(this, CalculatorActivity::class.java)
                    startActivity(intencalculator)

                }
            }

            true

        }


    }
    fun sumPoints(container: Container){
        if (ProjectApplication.prefs.getData().getName()?.isNotEmpty() == true) {
            val userPrefs = ProjectApplication.prefs.getData()
            val readerDb: SQLiteDatabase = recyclerDB.readableDatabase
            val cursor = readerDb.rawQuery(
                "SELECT name, points  FROM user where points >=0 or name = '" + userPrefs.getName() + "' limit 5",
                null
            )
            val listUsers = ArrayList<User>()
            if (cursor.moveToFirst()) {
                do {
                    val nameCursor: String = cursor.getString(0)
                    val pointsCursor: Int = cursor.getInt(1)
                    val userActual = User(nameCursor, "", pointsCursor, "mailCursor", 0)
                    listUsers.add(userActual)
                } while (cursor.moveToNext())
            }

            listUsers.forEach{ user ->

                if (user.getName() == userPrefs.getName() && container.getRecycled()!="yes"){
                    val userPoints = user.getPoints()
                    val totalPoints = userPoints?.plus(1)
                    if (totalPoints != null) {
                        recyclerDB.setPoints(user, totalPoints)
                        container.setRecycled("yes")
                        recyclerDB.setRecycled(container,"yes")
                    }


                }
            }

        }
    }
}
package com.msp.proyectomoises

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import database.RecyclerSQLiteHelper
import entities.Container

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
        val cursor = readerDb.rawQuery("SELECT code, type FROM container", null)
        val listContainers = ArrayList<Container>()
        var containerScanned = Container()

        if (cursor.moveToFirst()) {
            do {
                val codeCursor: String = cursor.getString(0)
                val typeCursor: String = cursor.getString(1)
                val containerActual = Container(codeCursor, typeCursor)
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
                }
                "glass" ->{
                    ivresult.setImageResource(R.drawable.container_glass)
                    tvResult.setText(R.string.TxtGlassResult)
                }
                "cardboard" ->{
                    ivresult.setImageResource(R.drawable.container_cardboard)
                    tvResult.setText(R.string.TxtCardboardResult)

                }
                "electronic" ->{
                    ivresult.setImageResource(R.drawable.ecopark)
                    tvResult.setText(R.string.TxtEcoparkResult)
                }


            }




        }

    }
}
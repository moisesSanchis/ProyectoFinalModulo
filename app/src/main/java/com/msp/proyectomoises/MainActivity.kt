package com.msp.proyectomoises

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import database.RecyclerDao
import database.RecyclerSQLiteHelper
import de.hdodenhof.circleimageview.CircleImageView
import entities.User

class MainActivity : AppCompatActivity() {
    lateinit var toogle:ActionBarDrawerToggle
    private lateinit var recyclerDao:RecyclerDao


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerDao = RecyclerDao(this)
        try {
            //Abrimos la base de datos.
            recyclerDao.open()

        }catch (e:Exception){
            Toast.makeText(baseContext, R.string.TxtErrorDataBase, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

        //Declaramos el controlador de la BBDD y accedemos en modo escritura.
        val dbHelper = RecyclerSQLiteHelper(applicationContext)
        val db = dbHelper.writableDatabase

        val drawerLayout:DrawerLayout = findViewById(R.id.drawerLayout)
        val navView:NavigationView = findViewById(R.id.nav_view)
        val userHeader:TextView = findViewById(R.id.tvUserMain)
        val circleImageView = findViewById<CircleImageView>(R.id.ciProfileMain)

      /*  val userMain = intent.getSerializableExtra("newUser", User::class.java)

        if (userMain !=null){
            userHeader.setText("Bienvenido " + userMain.getName())
            circleImageView.setImageResource(R.drawable.profile_test)

        }*/
        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navHome ->{
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)
                }
                R.id.navExit->  finishAffinity()
                R.id.navLogin -> {
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                R.id.navSettings -> Toast.makeText(this, "Has pulsado en ajustes del menu lateral", Toast.LENGTH_SHORT).show()

            }
            true
        }
       fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (toogle.onOptionsItemSelected(item)){
                return true
            }
            return super.onOptionsItemSelected(item)
        }
        val cvPackaging:CardView = findViewById(R.id.cvClassifyPackagingMain)
        val cvAwards:CardView = findViewById(R.id.cvAwardsMain)
        val cvLocate:CardView = findViewById(R.id.cvLocateMain)
        cvPackaging.setOnClickListener{
            val intentPackaging = Intent(this, ScannerActivity::class.java)
            startActivity(intentPackaging)

        }
        cvAwards.setOnClickListener {
            Toast.makeText(this, "Has pulsado en premios", Toast.LENGTH_SHORT).show()

        }
        cvLocate.setOnClickListener {
            val intentLocate = Intent(this, MapsActivity::class.java)
            startActivity(intentLocate)

        }
        val navigationView: BottomNavigationView = findViewById(R.id.menuNavigation)
        navigationView.selectedItemId = R.id.navigationHome
        navigationView.setOnItemSelectedListener { menuItem ->
            // Código a ejecutar cuando se seleccione un elemento del menú
            when (menuItem.itemId) {
                R.id.navigationHome -> {
                    // Código a ejecutar cuando se selecciona el elemento Home
                    Toast.makeText(this, "Has pulsado en Inicio", Toast.LENGTH_SHORT).show()

                }
                R.id.navigationLearn -> {
                    // Código a ejecutar cuando se selecciona el elemento search
                    Toast.makeText(this, "Has pulsado en Aprender", Toast.LENGTH_SHORT).show()

                }
                R.id.navigationCalculator -> {
                    // Código a ejecutar cuando se selecciona el elemento calculator
                    Toast.makeText(this, "Has pulsado en Calculadora", Toast.LENGTH_SHORT).show()

                }
            }

            true
        }





    }
}
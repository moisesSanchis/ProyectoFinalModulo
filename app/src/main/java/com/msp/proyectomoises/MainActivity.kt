package com.msp.proyectomoises

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import database.RecyclerDao
import database.RecyclerSQLiteHelper
import de.hdodenhof.circleimageview.CircleImageView
import entities.User
import preferences.ProjectApplication
import preferences.ProjectApplication.Companion.prefs

class MainActivity : AppCompatActivity() {
    private lateinit var toogle: ActionBarDrawerToggle
    private lateinit var recyclerDao: RecyclerDao
    private lateinit var profileMenu: CircleImageView
    private lateinit var userMenu: TextView
    private lateinit var mailMenu: TextView
    private lateinit var recyclerDB: RecyclerSQLiteHelper
    private var sound = false

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("ResourceType", "SetTextI18n", "MissingInflatedId", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerDB = RecyclerSQLiteHelper(this)
        //Gestion bd
        recyclerDao = RecyclerDao(this)
        try {
            //Abrimos la base de datos.
            recyclerDao.open()

        } catch (e: Exception) {
            Toast.makeText(baseContext, R.string.TxtErrorDataBase, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val header: View = navView.getHeaderView(0)
        profileMenu = header.findViewById(R.id.ciProfile)
        userMenu = header.findViewById(R.id.tvHeaderNavigationUser)
        mailMenu = header.findViewById(R.id.tvHeaderNavigationMail)
        val userHeader: TextView = findViewById(R.id.tvUserMain)
        val circleImageView = findViewById<CircleImageView>(R.id.ciProfileMain)

        //Gestion usuario.
       /* val userMain = intent.getSerializableExtra("newUser", User::class.java)

        if (userMain != null) {

            userMain.getName()
                ?.let {
                    userMain.getPoints()?.let { it1 ->
                        prefs.saveData(
                            it,
                            it1, userMain.getMail()!!, userMain.getImage()!!
                        )
                    }
                }


        }*/
        if (prefs.getData().getName()?.isNotEmpty() == true) {
            val userPrefs = prefs.getData()
            userHeader.text = getString(R.string.TxtWelcome) + " " + userPrefs.getName()
            val image = userPrefs.getImage()
            if (image != null) {
                circleImageView.setImageResource(image)
                profileMenu.setBackgroundResource(image)
            }

            userMenu.text = userPrefs.getName()
            mailMenu.text = userPrefs.getMail()

        } else {
            userHeader.text = getString(R.string.TxtWelcome) + " " + getString(R.string.TxtUser)
            circleImageView.setImageResource(R.drawable.icon_aplication)
        }


        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        //Listener del menu lateral
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navHome -> {
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)
                }

                R.id.navExit -> finishAffinity()
                R.id.navLogin -> {
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                }

                R.id.navSettings -> {
                    val intentSettings = Intent(applicationContext, SettingsActivity::class.java)
                    startActivity(intentSettings)
                }

                R.id.navLogOut -> {
                    prefs.wipeData()
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)

                }
            }
            true
        }

        fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (toogle.onOptionsItemSelected(item)) {
                return true
            }
            return super.onOptionsItemSelected(item)
        }

        val cvPackaging: CardView = findViewById(R.id.cvClassifyPackagingMain)
        val cvAwards: CardView = findViewById(R.id.cvAwardsMain)
        val cvLocate: CardView = findViewById(R.id.cvLocateMain)
        cvPackaging.setOnClickListener {
            music()
            val intentPackaging = Intent(this, ScannerActivity::class.java)
            startActivity(intentPackaging)

        }
        cvAwards.setOnClickListener {
            music()
            if (prefs.getData().getName()?.isNotEmpty() == true) {
                val userPrefs = prefs.getData()
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
                val intentAwards = Intent(this, AwardsActivity::class.java)
                intentAwards.putExtra("listUsers", listUsers)
                startActivity(intentAwards)

            } else {
                Toast.makeText(this, R.string.TxtAwardsRegister, Toast.LENGTH_SHORT).show()
            }

        }
        cvLocate.setOnClickListener {
            music()
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
                    /*  val intentMain = Intent(this, MainActivity::class.java)
                      startActivity(intentMain)*/

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

    private fun music() {
        val preferenceSound = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        val haveMusic = preferenceSound.getBoolean("reproducerSound", false)
        sound = haveMusic
        if (sound) {
            val mediaPlayer = MediaPlayer.create(this, R.raw.sound5)
            mediaPlayer.start()
        }
    }

}
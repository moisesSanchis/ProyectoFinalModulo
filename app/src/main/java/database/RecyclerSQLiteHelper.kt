package database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.msp.proyectomoises.R
import entities.Container
import entities.User

class RecyclerSQLiteHelper(context:Context):SQLiteOpenHelper(context, nameDB, factory, version) {
    companion object{
        private const val version = 1
        private const val nameDB = "RecyclerDB"
        private val factory: SQLiteDatabase.CursorFactory? = null
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Implementamos la lógica para crear la base de datos
        val sqlCreationTableContainer = "CREATE TABLE container(" + " code TEXT PRIMARY KEY,"+
                "type TEXT NOT NULL); "

        val sqlCreationTableUser = "CREATE TABLE user(" + "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "name TEXT NOT NULL," + "password TEXT NOT NULL," + "mail TEXT," + "points INTEGER," + "image INTEGER);"
        val imageTest = R.drawable.profile_test
        val sqlInsertDataDemo = arrayOf(
            "INSERT INTO user (id, name, password, mail, image) VALUES (0,'Moises', '1234', 'moises@gmail.com',$imageTest)",
            "INSERT INTO container (code, type) VALUES ('5449000000996', 'plastic')",
            "INSERT INTO container (code, type) VALUES ('8414807510341', 'plastic')",
            "INSERT INTO container (code, type) VALUES ('8414807522146', 'plastic')",
            "INSERT INTO container (code, type) VALUES ('8414807519740', 'cardboard')",
            "INSERT INTO container (code, type) VALUES ('8431876270358', 'cardboard')")


        //Se ejecutan las creaciones de las tablas.
        Log.i(this::class.java.toString(), "Creando las tablas")
        db?.execSQL(sqlCreationTableContainer)
        db?.execSQL(sqlCreationTableUser)
        Log.i(this::class.java.toString(), "Tablas creadas")

        //Se ejecuta la carga de datos iniciales.
        Log.i(this::class.java.toString(), "Insertando datos de demo")
        for (sqlInsert in sqlInsertDataDemo){
            db?.execSQL(sqlInsert)
        }
        Log.i(this::class.java.toString(), "Datos de demo cargados")
        Log.i(this::class.java.toString(), "Base de datos creada!")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //En esta funcion implementariamos la logica para actualizar la base de datos a una nueva version
     /*   val sqlInsertDataDemo = arrayOf( "INSERT INTO container (code, type) VALUES ('5449000000996', 'plastic')",
        "INSERT INTO container (code, type) VALUES ('8414807510341', 'plastic')",
        "INSERT INTO container (code, type) VALUES ('8414807522146', 'plastic')",
        "INSERT INTO container (code, type) VALUES ('8414807519740', 'cardboard')",
        "INSERT INTO container (code, type) VALUES ('8431876270358', 'cardboard')")
        for (sqlInsert in sqlInsertDataDemo){
            p0?.execSQL(sqlInsert)
        }*/
    }

    //Funcion para guardar usuarios en la base de datos
    fun setUser(user:User){
        val data = ContentValues()
        data.put("name", user.getName())
        data.put("password", user.getPassword())
        data.put("mail", user.getMail())
        data.put("points", user.getPoints())
        data.put("image", user.getImage())
        val db = this.writableDatabase
        db.insert("user", null, data)
        db.close()
    }

    //Funcion para guardar envases en la base de datos
    fun setContainer(container:Container){
        val data = ContentValues()
        data.put("code",container.getCode())
        data.put("type", container.getType().toString())
        val db = this.writableDatabase
        db.insert("container", null, data)
        db.close()
    }
}
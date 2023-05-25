package database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs

class RecyclerDao(private val context: Context) {
    companion object{
        const val C_TABLE = "container"
        const val C_COLUMN_CODE = "code"
        const val C_COLUMN_TYPE = "type"
        const val C_COLUMN_RECYCLED = "recycled"

        const val C_TABLE2 = "user"
        const val C_COLUMN_ID = "id"
        const val C_COLUMN_NAME = "name"
        const val C_COLUMN_PASSWORD = "password"
        const val C_COLUMN_MAIL = "mail"
        const val C_COLUMN_POINTS = "points"
        const val C_COLUMN_IMAGE = "image"
    }

    private lateinit var dbHelper: RecyclerSQLiteHelper
    private lateinit var db:SQLiteDatabase

    init {
        open()
    }

    fun open():RecyclerDao{
        dbHelper = RecyclerSQLiteHelper(context)
        db = dbHelper.writableDatabase
        return this

    }
    fun close(){
        dbHelper.close()
    }

    fun getCursor():Cursor{
        return db.query(
            true,
            C_TABLE,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }
    fun getCursor(sql:String, selectionArgs: Array<String>?):Cursor{
        return db.rawQuery(sql,selectionArgs)
    }
}
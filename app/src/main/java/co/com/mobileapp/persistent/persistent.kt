package co.com.mobileapp.persistent

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.json.JSONObject

/**
 * @author Jaime Gamboa
 * @see SQLiteOpenHelper
 */
class persistent (context: Context) : SQLiteOpenHelper(context, "offline.db", null, 1) {

    companion object {
        val id: String = "_id"
        val jsonObject: String = "json"
        val TABLE = "offline"
    }

    val DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE + " (" +
                    "${id} integer PRIMARY KEY autoincrement," +
                    "${jsonObject} text" +
                    ")"

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(DATABASE_CREATE);
    }

    fun insertItem (data: JSONObject) {
        val values = ContentValues()
        values.put(jsonObject, data.toString())
        writableDatabase.insert(TABLE, null, values);
    }

    fun getData () : Cursor {
        return readableDatabase.query(TABLE, arrayOf(jsonObject), null, null, null, null, null)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}

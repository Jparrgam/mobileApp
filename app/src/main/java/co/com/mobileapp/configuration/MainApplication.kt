package co.com.mobileapp.configuration

import android.app.Application
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import co.com.mobileapp.persistent.persistent
import org.json.JSONObject
import kotlin.properties.Delegates

/**
 * @author Jaime Gamboa
 * @see Application
 * @see persistent
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        dbHelper = persistent(this)
        db = dbHelper.writableDatabase
    }

    companion object {
        var dbHelper: persistent by Delegates.notNull()
        var db: SQLiteDatabase by Delegates.notNull()

        fun insertItem (text: JSONObject) {
            dbHelper.insertItem(text)
        }

        fun getCursor() : Cursor {
            return dbHelper.getData()
        }
    }
}
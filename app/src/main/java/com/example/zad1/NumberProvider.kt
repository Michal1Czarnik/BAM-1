package com.example.zad1

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NumberProvider : ContentProvider() {

    private lateinit var numberDao: NumberDao

    override fun onCreate(): Boolean {
        val db = Room.databaseBuilder(
            context!!.applicationContext,
            AppDatabase::class.java, "number-database"
        ).build()

        numberDao = db.numberDao()

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor {
        var cursor = MatrixCursor(arrayOf("id", "username", "counter"))

        runBlocking {
            val data = numberDao.getAll()
            data.forEach {
                cursor.addRow(arrayOf(it.uid, it.username, it.counter))
            }
        }

        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Not yet implemented")
    }
}

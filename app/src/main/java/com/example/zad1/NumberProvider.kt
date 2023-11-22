package com.example.zad1

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import androidx.room.Room
import android.content.Context

class NumberProvider : ContentProvider() {
    private lateinit var dataDao: NumberDao

    companion object {
        const val AUTHORITY = "com.example.zad1.provider"
        const val TAG = "content_provider"
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    init {
        Log.d(TAG, "ContentProvider initialization")
        // to access whole table
        uriMatcher.addURI(
            AUTHORITY,
            "data",
            0
        )
    }

    override fun onCreate(): Boolean {
        val db = Room.databaseBuilder(
            context!!.applicationContext,
            AppDatabase::class.java, "number-database"
        ).build()

        dataDao = db.numberDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {
        Log.d(TAG, "Query incoming...")
        val cursor = MatrixCursor(arrayOf("uid", "username", "counter"))

        when (uriMatcher.match(uri)) {
            0 -> {
                val data = dataDao.getAll()
                Log.d(TAG, "Response to the query: $data")
                data.forEach {
                    cursor.addRow(arrayOf(it.uid, it.username, it.counter))
                }
            }

            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}
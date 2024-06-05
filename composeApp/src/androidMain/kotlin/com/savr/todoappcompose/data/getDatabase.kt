package com.savr.todoappcompose.data

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.TaskDatabase

fun getDatabase(context: Context): TaskDatabase {
    val dbFile = context.getDatabasePath("task.db")
    return Room.databaseBuilder<TaskDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
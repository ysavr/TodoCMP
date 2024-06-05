package data

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getDatabase(): TaskDatabase {
    val dbFile = NSHomeDirectory() + "/task.db"
    return Room.databaseBuilder<TaskDatabase>(
        name = dbFile,
        factory = { TaskDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
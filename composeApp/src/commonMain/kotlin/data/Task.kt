package data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val task: String,
    val completed: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
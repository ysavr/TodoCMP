package data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val task: String,
    val completed: Boolean,
    val description: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
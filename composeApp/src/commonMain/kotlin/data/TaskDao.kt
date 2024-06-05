package data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Query("Update task SET task = :task, description = :description WHERE id = :id")
    suspend fun update(task: String, description: String, id: Int)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<Task>>

}
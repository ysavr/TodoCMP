package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Task
import data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.screen.TaskUiState

class TaskViewModel(val taskDao: TaskDao) : ViewModel() {

    private val _taskUIState = MutableStateFlow(TaskUiState(taskEntity = null))
    val taskUIState: StateFlow<TaskUiState> = _taskUIState.asStateFlow()

    fun getAllTask() {
        viewModelScope.launch {
            taskDao.getAllTask().collect {
                _taskUIState.update { uiState ->
                    uiState.copy(tasks = it)
                }
            }
        }
    }

    fun selectTask(task: Task) {
        _taskUIState.update {
            it.copy(taskEntity = task)
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }

    fun updateTask(
        task: String,
        description: String,
        id: Int
    ) {
        viewModelScope.launch {
            taskDao.update(
                task, description, id
            )
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }

    fun resetTask() {
        _taskUIState.update {
            it.copy(taskEntity = null)
        }
    }

}
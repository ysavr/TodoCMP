package presentation.screen

import data.Task

data class TaskUiState(
    val taskEntity: Task? = null,
    val tasks: List<Task> = emptyList()
)

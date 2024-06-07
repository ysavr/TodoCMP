package presentation.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.TaskDao
import presentation.TaskViewModel
import presentation.screen.home.HomeScreen
import presentation.screen.task.TaskScreen

@Composable
fun AppScreen(
    taskDao: TaskDao,
    navController: NavHostController = rememberNavController(),
) {

    val viewModel: TaskViewModel = viewModel { TaskViewModel(taskDao = taskDao) }

    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {
        composable(Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onAddTaskClicked = {
                    viewModel.resetTask()
                    navController.navigate(route = TaskRoute.route)
                },
                onTaskClicked = {
                    viewModel.selectTask(task = it)
                    navController.navigate(route = TaskRoute.route)
                }
            )
        }
        composable(TaskRoute.route) {
            TaskScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}
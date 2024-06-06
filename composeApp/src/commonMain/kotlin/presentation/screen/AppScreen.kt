package presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import data.TaskDao
import presentation.screen.home.HomeScreen
import presentation.screen.task.TaskScreen

@Composable
fun AppScreen(
    taskDao: TaskDao,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {
        composable(Home.route) {
            HomeScreen(
                taskDao = taskDao,
                onNextButtonClicked = {
                    navController.navigate(route = it)
                }
            )
        }
        composable(Task.route) {
            TaskScreen(
                taskDao = taskDao,
                navController = navController
            )
        }
    }
}
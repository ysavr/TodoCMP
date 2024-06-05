package presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.TaskDao
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.screen.task.TaskScreen
import todocmp.composeapp.generated.resources.Res
import todocmp.composeapp.generated.resources.delete

class HomeScreen(private val taskDao: TaskDao): Screen {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val tasks by taskDao.getAllTask().collectAsState(initial = emptyList())
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = { Text(text = "TODO Task") })
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navigator.push(TaskScreen(taskDao = taskDao))
                    },
                    shape = RoundedCornerShape(size = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon"
                    )
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 52.dp)
            ) {
                items(tasks.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navigator.push(TaskScreen(taskDao = taskDao, task = tasks[index]))
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                modifier = Modifier.alpha( 0.5f),
                                text = tasks[index].task,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                textDecoration = TextDecoration.None
                            )
                        }
                        IconButton(
                            onClick = {
                                scope.launch {
                                    taskDao.delete(tasks[index])
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    Res.drawable.delete
                                ),
                                contentDescription = "Delete Icon",
                                tint =  MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.38f
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

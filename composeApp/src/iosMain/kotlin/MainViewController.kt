import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import data.getDatabase

fun MainViewController() = ComposeUIViewController {
    val dao = remember {
        getDatabase().taskDao()
    }
    App(taskDao = dao)
}
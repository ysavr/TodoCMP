package presentation.screen

interface Destination {
    val route: String
}

object Home: Destination {
    override val route: String = "Home"
}

object Task: Destination {
    override val route: String = "Task"
}
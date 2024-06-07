package presentation.screen

interface Destination {
    val route: String
}

object Home: Destination {
    override val route: String = "Home"
}

object TaskRoute: Destination {
    override val route: String = "Task"
}
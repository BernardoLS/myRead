package poc.laguna.myread.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import poc.laguna.myread.R

sealed class AppRoutes(val name: String, val route: String, val icon: @Composable () -> Painter) {
    object ReadsRoute : AppRoutes("Leituras", "reads", { painterResource(R.drawable.book_ic) })
    object GroupsRoute : AppRoutes("Grupos", "groups", { painterResource(R.drawable.users_ic) })
    object DiscoveryRoute : AppRoutes("Descubra", "discover", { painterResource(R.drawable.search_ic) })
}

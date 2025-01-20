package poc.laguna.myread.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import poc.laguna.myread.R

sealed class BottomNavigationItem(val route: String, val icon: @Composable () -> Painter, val label: String) {
    object Reads : BottomNavigationItem("reads", { painterResource(R.drawable.book_ic) }, "Leituras")
    object Groups : BottomNavigationItem("groups", { painterResource(R.drawable.users_ic) }, "Grupos")
    object Discover : BottomNavigationItem("discover", { painterResource(R.drawable.search_ic) }, "Descubra")
}
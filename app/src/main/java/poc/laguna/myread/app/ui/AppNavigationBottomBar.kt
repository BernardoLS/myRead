package poc.laguna.myread.app.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import poc.laguna.myread.app.ui.navigation.AppRoutes

@Composable
fun AppNavigationBottomBar(
    navigationController: NavController
) {
    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }
    val topLevelRoutes = listOf(
        AppRoutes.ReadsRoute,
        AppRoutes.GroupsRoute,
        AppRoutes.DiscoveryRoute
    )
    NavigationBar {
     topLevelRoutes.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = index == navigationSelectedItem,
                icon = { Icon(painter = navItem.icon(), contentDescription = navItem.name) },
                label = { Text(text = navItem.name) },
                onClick = {
                    navigationSelectedItem = index
                    navigationController.navigate(navItem.route) {
                        popUpTo(navigationController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


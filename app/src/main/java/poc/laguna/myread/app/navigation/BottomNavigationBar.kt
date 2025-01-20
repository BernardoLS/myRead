package poc.laguna.myread.app.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import poc.laguna.myread.core.ui.components.BottomNavigationItem

@Composable
fun BottomNavigationBar(navigationController: NavController) {
    val items = listOf(
        BottomNavigationItem.Reads,
        BottomNavigationItem.Groups,
        BottomNavigationItem.Discover
    )
    NavigationBar {
        val currentRoute = navigationController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painter = item.icon(), contentDescription = item.label)},
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                onClick =  {
                    if (currentRoute != item.route) {
                        navigationController.navigate(item.route) {
                            popUpTo(navigationController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )

        }
    }
}
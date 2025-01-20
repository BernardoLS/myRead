package poc.laguna.myread.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import poc.laguna.myread.core.ui.components.BottomNavigationItem
import poc.laguna.myread.modules.discover.ui.DiscoverPage
import poc.laguna.myread.modules.groups.ui.GroupsPage
import poc.laguna.myread.modules.reads.ui.ReadsPage

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationItem.Reads.route,
        modifier = modifier
    ) {
        composable(BottomNavigationItem.Reads.route) {
            ReadsPage()
        }
        composable(BottomNavigationItem.Groups.route) {
            GroupsPage()
        }
        composable(BottomNavigationItem.Discover.route) {
            DiscoverPage()
        }
    }
}
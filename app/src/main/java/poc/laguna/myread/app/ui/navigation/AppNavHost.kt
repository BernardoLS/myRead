package poc.laguna.myread.app.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import poc.laguna.myread.app.ui.navigation.routeModels.ReadDetailRoute
import poc.laguna.myread.modules.discover.ui.DiscoverPage
import poc.laguna.myread.modules.groups.ui.GroupsPage
import poc.laguna.myread.modules.reads.readDetailPage.ReadDetailPage
import poc.laguna.myread.modules.reads.readsPage.ui.ReadsPage

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ) {
        navigation(startDestination = AppRoutes.ReadsRoute.route, "main") {
            composable(AppRoutes.ReadsRoute.route) { ReadsPage() }
            composable(AppRoutes.GroupsRoute.route){ GroupsPage() }
            composable(AppRoutes.DiscoveryRoute.route) { DiscoverPage() }
        }

        composable<ReadDetailRoute> {
            val args = it.toRoute<ReadDetailRoute>()
            ReadDetailPage(bookId = args.bookId,
                modifier = Modifier
                .fillMaxSize()
            )
        }
    }
}
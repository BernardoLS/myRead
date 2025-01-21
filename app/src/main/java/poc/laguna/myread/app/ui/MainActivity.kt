package poc.laguna.myread.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import poc.laguna.myread.app.ui.navigation.AppNavHost
import poc.laguna.myread.app.ui.navigation.AppRoutes
import poc.laguna.myread.app.ui.navigation.routeModels.DiscoverRoute
import poc.laguna.myread.app.ui.navigation.routeModels.GroupsRoute
import poc.laguna.myread.app.ui.navigation.routeModels.ReadsRoute
import poc.laguna.myread.core.ui.theme.MyReadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyReadTheme {
                val navigationController = rememberNavController()
                val currentDestination =
                    navigationController.currentBackStackEntryAsState().value?.destination
                val isBottomBarVisible = when (currentDestination?.route) {
                    AppRoutes.ReadsRoute.route,
                    AppRoutes.GroupsRoute.route,
                    AppRoutes.DiscoveryRoute.route -> true
                    else -> false
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (isBottomBarVisible) {
                            AppNavigationBottomBar(navigationController = navigationController)
                        }
                    }) { innerPadding ->
                    AppNavHost(
                       navController = navigationController,
                       modifier = Modifier.padding(innerPadding)
                   )
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyReadTheme {
//        val navigationController = rememberNavController()
//        Scaffold(
//            modifier = Modifier.fillMaxSize(),
//            bottomBar = {
//                BottomNavigationBar(navigationController = navigationController)
//            }
//        ) { innerPadding ->
//            AppNavHost(
//                navController = navigationController,
//                modifier = Modifier.padding(innerPadding)
//            )
//        }
//    }
//}
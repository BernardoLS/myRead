package poc.laguna.myread.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import poc.laguna.myread.app.ui.navigation.AppNavHost
import poc.laguna.myread.app.ui.navigation.AppRoutes
import poc.laguna.myread.app.ui.navigation.LocalNavController
import poc.laguna.myread.core.ui.theme.MyReadTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()

            CompositionLocalProvider(LocalNavController provides navigationController) {
                MyReadTheme {

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
}
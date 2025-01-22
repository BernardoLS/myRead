package poc.laguna.myread.app.ui.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not provided. Make sure to provide it in your App.")
}
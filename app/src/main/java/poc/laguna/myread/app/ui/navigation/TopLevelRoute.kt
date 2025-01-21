package poc.laguna.myread.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: @Composable () -> Painter)

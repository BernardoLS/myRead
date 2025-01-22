package poc.laguna.myread.modules.reads.readsPage.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import poc.laguna.myread.app.ui.navigation.LocalNavController
import poc.laguna.myread.app.ui.navigation.routeModels.ReadDetailRoute
import poc.laguna.myread.modules.reads.components.HighlightsCarousel

@Composable
fun ReadsPage() {
    val navController = LocalNavController.current
    PageBody(navController)
}

@Composable
private fun PageBody(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(padding)
        ) {
            Text("Página de Leitura", modifier = Modifier.padding(16.dp).clickable {
                navController.navigate(ReadDetailRoute("Book Id"))
            })

        }
    }
}

@Preview
@Composable
fun PreviewReadPage() {
    PageBody(rememberNavController())
}
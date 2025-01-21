package poc.laguna.myread.modules.reads.ui.readsPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import poc.laguna.myread.app.ui.navigation.routeModels.ReadDetailRoute

@Composable
fun ReadsPage(navController: NavController) {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        LazyRow {
            items(items.size) { index ->
                Text(text = items[index],
                    modifier = Modifier.padding(8.dp)
                        .clickable {
                            navController.navigate(ReadDetailRoute(items[index]))
                        },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
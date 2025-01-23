package poc.laguna.myread.modules.reads.readsPage.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel

@Composable
fun HighlightsCarousel(highlightList: List<HighlightModel>) {
    Row {
        highlightList.forEach {
            Text(it.id)
        }
    }
}
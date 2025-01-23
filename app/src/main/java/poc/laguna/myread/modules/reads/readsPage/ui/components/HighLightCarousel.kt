package poc.laguna.myread.modules.reads.readsPage.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel

@Composable
fun HighlightsCarousel(highlightList: List<HighlightModel>, onClickListener: (HighlightModel) -> Unit) {
    LazyRow {
        items(highlightList) { highlight ->
            Text(
                text = highlight.id,
                modifier = Modifier
                    .clickable { onClickListener(highlight) }
                    .padding(8.dp)
            )
        }
    }
}
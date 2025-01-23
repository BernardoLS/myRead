package poc.laguna.myread.modules.reads.readsPage.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import poc.laguna.myread.app.ui.navigation.LocalNavController
import poc.laguna.myread.core.ui.components.LoadingView
import poc.laguna.myread.modules.reads.readsPage.ui.components.HighlightError
import poc.laguna.myread.modules.reads.readsPage.ui.components.HighlightsCarousel
import poc.laguna.myread.modules.reads.readsPage.ui.states.HighlightState

@Composable
fun ReadsPage(viewModel: ReadsPageViewModel = koinViewModel()) {
    val highlightsState by viewModel.highlightsState.collectAsState()

    val navController = LocalNavController.current

    LaunchedEffect(Unit) {
        viewModel.sendIntent(ReadPageIntents.LoadData)
        viewModel.navigationEvent.collect {
            navController.navigate(it)
        }
    }

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                when (val state = highlightsState) {
                   is HighlightState.Loading -> LoadingView()
                   is HighlightState.Success -> HighlightsCarousel(state.highlights) {
                       viewModel.sendIntent(ReadPageIntents.NavigateToHighlightDetail(it.id))
                   }
                   is HighlightState.Error -> HighlightError(state.message)
                }
            }
        }
    }
}
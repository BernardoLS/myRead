package poc.laguna.myread.modules.reads.readsPage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import poc.laguna.myread.app.ui.navigation.routeModels.AppRoute
import poc.laguna.myread.app.ui.navigation.routeModels.ReadDetailRoute
import poc.laguna.myread.modules.reads.readsPage.domain.FetchHighlightsUseCase
import poc.laguna.myread.modules.reads.readsPage.ui.states.HighlightState

class ReadsPageViewModel(private val fetchHighlightsUseCase: FetchHighlightsUseCase) : ViewModel() {
    private val _highlightsState = MutableStateFlow<HighlightState>(HighlightState.Loading)
    val highlightsState: StateFlow<HighlightState> = _highlightsState

    private val _navigationEvent = MutableSharedFlow<AppRoute>()
    val navigationEvent: SharedFlow<AppRoute> = _navigationEvent

    private val intentChannel = Channel<ReadPageIntents>(Channel.UNLIMITED)

    init {
        processIntents()
    }

    private fun fetchHighlights() {
        viewModelScope.launch {
            _highlightsState.value = HighlightState.Loading

            val result = runCatching {
                fetchHighlightsUseCase()
            }

            result.onSuccess {
                _highlightsState.value = HighlightState.Success(it)
            }.onFailure {
                _highlightsState.value = HighlightState.Error(it.message ?: "Unknown error")
            }
        }
    }

    fun sendIntent(intent: ReadPageIntents) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    private fun processIntents() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is ReadPageIntents.LoadData -> fetchHighlights()
                    is ReadPageIntents.NavigateToHighlightDetail -> navigateTo(ReadDetailRoute(intent.highlightId))
                    is ReadPageIntents.NavigateToBookDetail -> navigateTo(ReadDetailRoute(intent.bookId))
                }
            }
        }
    }

    private fun navigateTo(route: AppRoute) {
        viewModelScope.launch {
            _navigationEvent.emit(route)
        }
    }
}
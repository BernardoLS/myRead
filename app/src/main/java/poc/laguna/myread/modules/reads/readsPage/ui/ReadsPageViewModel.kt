package poc.laguna.myread.modules.reads.readsPage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import poc.laguna.myread.modules.reads.readsPage.domain.FetchHighlightsUseCase
import poc.laguna.myread.modules.reads.readsPage.ui.states.HighlightState

class ReadsPageViewModel(private val fetchHighlightsUseCase: FetchHighlightsUseCase) : ViewModel() {
    private val _highlightsState = MutableStateFlow<HighlightState>(HighlightState.Loading)
    val highlightsState: StateFlow<HighlightState> = _highlightsState

    fun fetchHighlights() {
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
}
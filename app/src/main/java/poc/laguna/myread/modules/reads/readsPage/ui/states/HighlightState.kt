package poc.laguna.myread.modules.reads.readsPage.ui.states

import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel

interface HighlightState {
    object Loading : HighlightState
    data class Success(val highlights: List<HighlightModel>) : HighlightState
    data class Error(val message: String) : HighlightState
}
package poc.laguna.myread.modules.reads.readsPage.ui

sealed interface ReadPageIntents {
    data object LoadData : ReadPageIntents
    data class NavigateToHighlightDetail(val highlightId: String) : ReadPageIntents
    data class NavigateToBookDetail(val bookId: String) : ReadPageIntents
}
package poc.laguna.myread.modules.reads.readsPage.domain

import kotlinx.serialization.Serializable

@Serializable
data class HighlightModel(
    val id: String,
    val highlightsUrl: String
)

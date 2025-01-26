package poc.laguna.myread.modules.reads.readsPage.data.responses

import kotlinx.serialization.Serializable
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel

@Serializable
data class HighlightResponse(val id: String, val highlightsUrl: String)

fun HighlightResponse.toDomain() : HighlightModel {
    return HighlightModel(
        id = id,
        highlightsUrl = highlightsUrl
    )
}
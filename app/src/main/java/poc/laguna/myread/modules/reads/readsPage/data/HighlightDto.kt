package poc.laguna.myread.modules.reads.readsPage.data

import kotlinx.serialization.Serializable
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel

@Serializable
data class HighlightDto(val id: String, val highlightsUrl: String)

fun HighlightDto.toDomain() : HighlightModel {
    return HighlightModel(
        id = id,
        highlightsUrl = highlightsUrl
    )
}
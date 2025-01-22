package poc.laguna.myread.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class HighLightModel(val id: String, val highlightsUrl: String)

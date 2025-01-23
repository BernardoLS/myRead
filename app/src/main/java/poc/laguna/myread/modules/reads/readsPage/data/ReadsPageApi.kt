package poc.laguna.myread.modules.reads.readsPage.data

import poc.laguna.myread.core.domain.HighLightModel
import retrofit2.http.GET

interface ReadsPageApi {
    @GET("4028c560-727c-4c28-931b-e67ccca56fec")
    suspend fun fetchHighlights(): List<HighLightModel>
}
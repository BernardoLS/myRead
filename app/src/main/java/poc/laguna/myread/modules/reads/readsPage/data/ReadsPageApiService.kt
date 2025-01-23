package poc.laguna.myread.modules.reads.readsPage.data

import retrofit2.http.GET

interface ReadsPageApiService {
    @GET("4028c560-727c-4c28-931b-e67ccca56fec")
    suspend fun fetchHighlights(): List<HighlightDto>
}
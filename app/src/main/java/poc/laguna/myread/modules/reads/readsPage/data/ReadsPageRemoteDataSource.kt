package poc.laguna.myread.modules.reads.readsPage.data

import poc.laguna.myread.core.data.wrappers.ResultHandler
import poc.laguna.myread.core.data.wrappers.safeApiRequest
import poc.laguna.myread.modules.reads.readsPage.data.responses.HighlightResponse
import poc.laguna.myread.modules.reads.readsPage.data.services.ReadsPageApiService

class ReadsPageRemoteDataSource(private val apiService: ReadsPageApiService) {
    suspend fun fetchHighlights(): ResultHandler<List<HighlightResponse>> {
        return safeApiRequest { apiService.fetchHighlights() }
    }
}
package poc.laguna.myread.modules.reads.readsPage.data

import poc.laguna.myread.core.data.wrappers.ResultHandler
import poc.laguna.myread.core.data.wrappers.SafeApiRequest
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel
import poc.laguna.myread.modules.reads.readsPage.domain.ReadsPageRepositoryInterface

class ReadsPageRepository(private val api: ReadsPageApiService) : ReadsPageRepositoryInterface {
    override suspend fun fetchHighlights(): List<HighlightModel> {
        return when (val result = SafeApiRequest { api.fetchHighlights() }) {
            is ResultHandler.Success -> result.value.map { it.toDomain() }
            is ResultHandler.Error -> throw result.throwable
        }
    }
}
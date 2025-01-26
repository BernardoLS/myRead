package poc.laguna.myread.modules.reads.readsPage.data

import poc.laguna.myread.core.data.wrappers.ResultHandler
import poc.laguna.myread.core.data.wrappers.safeApiRequest
import poc.laguna.myread.modules.reads.readsPage.data.responses.toDomain
import poc.laguna.myread.modules.reads.readsPage.data.services.ReadsPageApiService
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel
import poc.laguna.myread.modules.reads.readsPage.domain.ReadsPageRepositoryInterface

class ReadsPageRepository(private val dataSource: ReadsPageRemoteDataSource) : ReadsPageRepositoryInterface {
    override suspend fun fetchHighlights(): List<HighlightModel> {
        return when (val result = dataSource.fetchHighlights()) {
            is ResultHandler.Success -> result.value.map { it.toDomain() }
            is ResultHandler.Error -> arrayListOf()
        }
    }
}
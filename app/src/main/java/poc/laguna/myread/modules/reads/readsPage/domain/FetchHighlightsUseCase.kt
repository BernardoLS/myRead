package poc.laguna.myread.modules.reads.readsPage.domain

import poc.laguna.myread.core.domain.HighLightModel

class FetchHighlightsUseCase(private val repository: ReadsPageRepositoryInterface) {
    suspend fun invoke(): List<HighLightModel> {
        return repository.fetchHighlights()
    }
}
package poc.laguna.myread.modules.reads.readsPage.domain

class FetchHighlightsUseCase(private val repository: ReadsPageRepositoryInterface) {
    suspend operator fun invoke(): List<HighlightModel> {
        return try {
            repository.fetchHighlights()
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}
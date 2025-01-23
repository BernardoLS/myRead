package poc.laguna.myread.modules.reads.readsPage.domain

interface ReadsPageRepositoryInterface {
   suspend fun fetchHighlights(): List<HighlightModel>
}
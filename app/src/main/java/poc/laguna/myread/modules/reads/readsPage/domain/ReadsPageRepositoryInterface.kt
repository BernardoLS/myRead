package poc.laguna.myread.modules.reads.readsPage.domain

import poc.laguna.myread.core.domain.HighLightModel

interface ReadsPageRepositoryInterface {
   suspend fun fetchHighlights(): List<HighLightModel>
}
package poc.laguna.myread.modules.reads.readsPage.data

import poc.laguna.myread.core.domain.HighLightModel
import poc.laguna.myread.modules.reads.readsPage.domain.ReadsPageRepositoryInterface

class ReadsPageRepository(private val api: ReadsPageApi) : ReadsPageRepositoryInterface {
    override suspend fun fetchHighlights(): List<HighLightModel> {
        return api.fetchHighlights()
    }
}
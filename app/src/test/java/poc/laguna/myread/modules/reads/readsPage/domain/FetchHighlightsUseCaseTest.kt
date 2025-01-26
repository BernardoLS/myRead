package poc.laguna.myread.modules.reads.readsPage.domain

import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class FetchHighlightsUseCaseTest {
    private val mockRepository = mockk<ReadsPageRepositoryInterface>()
    private lateinit var sut: FetchHighlightsUseCase
}
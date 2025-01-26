package poc.laguna.myread.modules.reads.readsPage.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class FetchHighlightsUseCaseTest {
    private val mockRepository = mockk<ReadsPageRepositoryInterface>()
    private lateinit var sut: FetchHighlightsUseCase
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = FetchHighlightsUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetch is successful, then result should be a list of HighlightModel`() = testScope.runTest {
        val response = arrayListOf(HighlightModel("id", "url"))

        coEvery { mockRepository.fetchHighlights() } returns response

        val result = sut()

        advanceUntilIdle()

        assertEquals(response, result)
    }

    @Test
    fun `when fetch is unsuccessful, then result should be an empty list`() = testScope.runTest {
        coEvery { mockRepository.fetchHighlights() } throws Exception("error")

        val result = sut()

        advanceUntilIdle()

        assertEquals(arrayListOf(), result)
    }
}
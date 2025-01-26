package poc.laguna.myread.modules.reads.readsPage.data

import io.mockk.coEvery
import io.mockk.coVerify
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
import poc.laguna.myread.core.data.wrappers.ResultHandler
import poc.laguna.myread.modules.reads.readsPage.data.responses.HighlightResponse
import poc.laguna.myread.modules.reads.readsPage.data.services.ReadsPageApiService
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ReadsPageRemoteDataSourceTest {

    private lateinit var sut: ReadsPageRemoteDataSource
    private val mockApi = mockk<ReadsPageApiService>()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        sut = ReadsPageRemoteDataSource(mockApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetch highlights with success should return a list of HighlightResponse`()  = testScope.runTest {
        val expectedResult = listOf(
            HighlightResponse(
                id = "123",
                highlightsUrl = "http://example.com"
            ),
            HighlightResponse(
                id = "321",
                highlightsUrl = "http://example.com"
            )
        )

        coEvery { mockApi.fetchHighlights() } returns expectedResult
        advanceUntilIdle()
        val result = sut.fetchHighlights()

        result as ResultHandler.Success

        assertEquals(result.value, expectedResult)
        coVerify(exactly = 1) { mockApi.fetchHighlights() }
    }

    @Test
    fun `when fetch highlights with error should throw an error`() = testScope.runTest {
        val error = Throwable("error")

        coEvery { mockApi.fetchHighlights() } throws error

        advanceUntilIdle()
        val result = sut.fetchHighlights()

        result as ResultHandler.Error
        assertEquals(result.throwable, error)
        coVerify(exactly = 1) { mockApi.fetchHighlights() }
    }
}
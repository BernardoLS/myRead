package poc.laguna.myread.modules.reads.readsPage.data

import androidx.compose.ui.graphics.evaluateCubic
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
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
import poc.laguna.myread.modules.reads.readsPage.data.responses.toDomain
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel

@OptIn(ExperimentalCoroutinesApi::class)
class ReadsPageRepositoryTest {
    private val mockRemoteDataSource = mockk<ReadsPageRemoteDataSource>()
    private lateinit var sut: ReadsPageRepository

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = ReadsPageRepository(mockRemoteDataSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetchHighlights is called should return a list of HighlightModel`() = testScope.runTest {
        val mockResponse = ResultHandler.Success(listOf(HighlightResponse(id = "id", highlightsUrl = "fakeUrl")))
        val expected = mockResponse.value.map { it.toDomain() }

        coEvery { mockRemoteDataSource.fetchHighlights() } returns mockResponse
        val result = sut.fetchHighlights()

        advanceUntilIdle()

        assertEquals(result, expected)
    }

    @Test
    fun `when fetchHighlights is called with error should throw an error`() = testScope.runTest{
        val expected = ResultHandler.Error(Throwable("Error"))

        coEvery { mockRemoteDataSource.fetchHighlights() } returns expected

        val result = sut.fetchHighlights()
        advanceUntilIdle()
        assertEquals(result, arrayListOf<HighlightModel>())
    }
}
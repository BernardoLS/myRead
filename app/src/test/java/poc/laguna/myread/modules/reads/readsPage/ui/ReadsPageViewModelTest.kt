package poc.laguna.myread.modules.reads.readsPage.ui

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import poc.laguna.myread.app.ui.navigation.routeModels.ReadDetailRoute
import poc.laguna.myread.modules.reads.readsPage.domain.FetchHighlightsUseCase
import poc.laguna.myread.modules.reads.readsPage.domain.HighlightModel
import poc.laguna.myread.modules.reads.readsPage.ui.states.HighlightState
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ReadsPageViewModelTest {
    private val mockUseCase = mockk<FetchHighlightsUseCase>()
    private lateinit var sut: ReadsPageViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = ReadsPageViewModel(mockUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when intent is loadPage and result is success, then state should be Success`() = testScope.runTest {
        val expected = listOf(HighlightModel(
            "id1", "url1"),
            HighlightModel("id2", "url2")
        )

        coEvery { mockUseCase() } returns expected

        sut.sendIntent(ReadPageIntents.LoadData)

        advanceUntilIdle()

        assertEquals(HighlightState.Success(expected), sut.highlightsState.value)
    }

    @Test
    fun `when intent is loadPage and result is empty, then state should be Empty`() = testScope.runTest {
        coEvery { mockUseCase() } returns arrayListOf()

        sut.sendIntent(ReadPageIntents.LoadData)

        advanceUntilIdle()

        assertEquals(HighlightState.Empty, sut.highlightsState.value)
    }

    @Test
    fun `when intent is loadPage and result is error, then state should be Error`() = testScope.runTest {
        coEvery { mockUseCase() } throws Throwable("error")

        sut.sendIntent(ReadPageIntents.LoadData)

        advanceUntilIdle()

        assertEquals(HighlightState.Error("error"), sut.highlightsState.value)
    }

    @Test
    fun `when intent is navigateToHighlightDetail, then navigationEvent should be triggered with highlightId`() = testScope.runTest {
        val highlightId = "1"
        val expected = ReadDetailRoute(highlightId)

        sut.sendIntent(ReadPageIntents.NavigateToBookDetail(highlightId))

        val navigationEvent = sut.navigationEvent.first()
        advanceUntilIdle()

        assertEquals(expected, navigationEvent)
    }

    @Test
    fun `when intent is navigateToBookDetails, then navigationEvent should be triggered with bookId`() = testScope.runTest {
        val bookId = "2"
        val expected = ReadDetailRoute(bookId)

        sut.sendIntent(ReadPageIntents.NavigateToBookDetail(bookId))

        val navigationEvent = sut.navigationEvent.first()
        advanceUntilIdle()

        assertEquals(expected, navigationEvent)
    }
}

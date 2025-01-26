package poc.laguna.myread.modules.reads.readsPage.data

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import poc.laguna.myread.core.data.wrappers.ResultHandler
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
    fun `when fetchHighlights is called should return a list of HighlightModel`() {

    }

    @Test
    fun `when fetchHighlights is called with error should throw an error`() {

    }
}
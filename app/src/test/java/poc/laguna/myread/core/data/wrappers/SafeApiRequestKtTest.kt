package poc.laguna.myread.core.data.wrappers

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
import poc.laguna.myread.core.data.mocks.FakeApiWithRequestInterface
import poc.laguna.myread.core.data.mocks.FakeRepository
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SafeApiRequestKtTest {
    private val fakeApi = mockk<FakeApiWithRequestInterface>()
    private val fakeRepository = FakeRepository(fakeApi)

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
       Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when request is successful should return ResultHandler as success`() = testScope.runTest {
        coEvery { fakeApi.doRequest() } returns Unit

        val response = fakeRepository.doRequest()

        advanceUntilIdle()

        response as ResultHandler.Success
        assertEquals(response.value, Unit )
    }

    @Test
    fun `when request is failed should return ResultHandler as error`() = testScope.runTest {
        val error = Throwable("error")

        coEvery { fakeApi.doRequest() } throws error

        val response = fakeRepository.doRequest()
        advanceUntilIdle()
        response as ResultHandler.Error
        assertEquals(response.throwable, error)
    }
}
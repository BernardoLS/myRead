package poc.laguna.myread.core.data.wrappers

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import poc.laguna.myread.core.data.mocks.FakeApiWithRequestInterface
import poc.laguna.myread.core.data.mocks.FakeRepository
import kotlin.test.assertEquals

class SafeApiRequestKtTest {
    private val fakeApi = mockk<FakeApiWithRequestInterface>()
    private val fakeRepository = FakeRepository(fakeApi)

    @Test
    fun `when request is successful should return ResultHandler as success`() = runBlocking {
        coEvery { fakeApi.doRequest() } returns Unit

        val response = fakeRepository.doRequest()

        response as ResultHandler.Success
        assertEquals(response.value, Unit )
    }

    @Test
    fun `when request is failed should return ResultHandler as error`() = runBlocking {
        val error = Throwable("error")

        coEvery { fakeApi.doRequest() } throws error

        val response = fakeRepository.doRequest()

        response as ResultHandler.Error
        assertEquals(response.throwable, error)
    }
}
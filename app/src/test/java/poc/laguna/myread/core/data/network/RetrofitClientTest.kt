package poc.laguna.myread.core.data.network

import io.mockk.every
import io.mockk.mockk
import okhttp3.OkHttpClient
import org.junit.Test
import poc.laguna.myread.core.data.mocks.FakeApiInterface
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class RetrofitClientTest  {

    @Test
    fun `should instance retrofit properly`() {
        val mockInterceptor = mockk<ClientInterceptor>()
        val mockOkHttpClient = mockk<OkHttpClient>()

        every { mockInterceptor.buildClient() } returns mockOkHttpClient

        val retrofit = RetrofitClient.instance(
            "http://localhost:8080",
            FakeApiInterface::class.java,
            mockInterceptor.buildClient()
            )

        assertNotNull(retrofit)
        assertIs<FakeApiInterface>(retrofit)
    }
}
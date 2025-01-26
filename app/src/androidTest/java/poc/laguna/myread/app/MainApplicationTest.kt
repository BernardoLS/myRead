import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import poc.laguna.myread.app.MainApplication
import poc.laguna.myread.modules.reads.readsPage.data.services.ReadsPageApiService

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MainApplicationTest : KoinTest {

    private lateinit var application: MainApplication

    private val mockApiService = mockk<ReadsPageApiService>()

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single { mockApiService }
                }
            )
        }

        application = RuntimeEnvironment.application as MainApplication
    }

    @Test
    fun testKoinInitialization() {
        assertNotNull(GlobalContext.getOrNull())

        val apiService = GlobalContext.get().getOrNull<ReadsPageApiService>()
        assertNotNull(apiService)
    }
}
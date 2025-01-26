package poc.laguna.myread.app

import android.app.Application
import io.mockk.mockk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import poc.laguna.myread.app.di.getApiModules
import poc.laguna.myread.modules.reads.readsPage.domain.ReadsPageRepositoryInterface

class NonInstrumentedTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        configKoin()
    }

    private fun configKoin() {
        val mockModule = module {
            single { mockk<ReadsPageRepositoryInterface>() }
        }
        startKoin {
            androidContext(this@NonInstrumentedTestApplication)
            modules(mockModule + getApiModules("https://localhost:8080"))
        }
    }
}

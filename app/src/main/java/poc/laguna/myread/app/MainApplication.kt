package poc.laguna.myread.app

import android.app.Application
import org.koin.core.context.startKoin
import poc.laguna.myread.app.di.appModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}
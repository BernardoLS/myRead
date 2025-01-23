package poc.laguna.myread.app.di

import org.koin.dsl.module
import poc.laguna.myread.core.data.network.ClientInterceptor
import poc.laguna.myread.core.data.network.RetrofitClient
import poc.laguna.myread.modules.reads.readsPage.data.ReadsPageApi
import poc.laguna.myread.modules.reads.readsPage.data.ReadsPageRepository
import poc.laguna.myread.modules.reads.readsPage.domain.FetchHighlightsUseCase

val appModule = module {
    single { ReadsPageRepository(get()) }
    factory { FetchHighlightsUseCase(get()) }
    //factory { ReadsPageViewModel(get()) }
}

fun getApiModules(baseUrl: String) = module {
    factory { RetrofitClient.instance(baseUrl, ReadsPageApi::class.java, ClientInterceptor.buildClient()) }
}
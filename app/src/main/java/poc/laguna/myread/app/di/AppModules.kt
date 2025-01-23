package poc.laguna.myread.app.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import poc.laguna.myread.core.data.network.ClientInterceptor
import poc.laguna.myread.core.data.network.RetrofitClient
import poc.laguna.myread.modules.reads.readsPage.data.ReadsPageApiService
import poc.laguna.myread.modules.reads.readsPage.data.ReadsPageRepository
import poc.laguna.myread.modules.reads.readsPage.domain.FetchHighlightsUseCase
import poc.laguna.myread.modules.reads.readsPage.domain.ReadsPageRepositoryInterface
import poc.laguna.myread.modules.reads.readsPage.ui.ReadsPageViewModel
import retrofit2.Retrofit

val appModule = module {
    single<ReadsPageRepositoryInterface> { ReadsPageRepository(get()) }
    factory { FetchHighlightsUseCase(get()) }
    viewModel { ReadsPageViewModel(get()) }
}

fun getApiModules(baseUrl: String) = module {
    factory { RetrofitClient
        .instance(baseUrl, ReadsPageApiService::class.java, ClientInterceptor.buildClient()) }

}
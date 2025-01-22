package poc.laguna.myread.core.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitClient {
    fun <T> instance(baseUrl: String, apiClass: Class<T>, client: OkHttpClient): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiClass)
    }
}
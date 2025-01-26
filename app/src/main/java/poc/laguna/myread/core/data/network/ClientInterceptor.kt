package poc.laguna.myread.core.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal object ClientInterceptor {
    fun buildClient() = OkHttpClient
        .Builder()
        .addInterceptor(setUpLoggingLevel())
        .build()


    private fun setUpLoggingLevel(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}


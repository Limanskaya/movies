package www.movies.app.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitService {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", "4dee19cf079cf1eec5d454cf78ab90f1")
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }


    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }


    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(authInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()


    private fun retrofit() : Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .build()


    val api : API = retrofit().create(API::class.java)
}
package com.example.ntg_task.data.di

import com.example.ntg_task.BuildConfig
import com.example.ntg_task.data.di.NetworkModule.md5
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.security.MessageDigest
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Singleton
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val client = OkHttpClient.Builder()
        client.apply {
            addInterceptor(ApiInterceptor())
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(loggingInterceptor)
            }
        }

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .client(client.build())
            .build()
    }

    val String.md5: String
        get() {
            val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }
}

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
        val newRequestHttpUrl = newRequest.url
        val timestamp = (Calendar.getInstance(
            TimeZone.getTimeZone("ts")
        ).timeInMillis / 1000L).toString()
        val url = newRequestHttpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter(
                "hash",
                "$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}".md5
            )
            .build()
        return chain.proceed(newRequest.newBuilder().url(url).build())

    }
}
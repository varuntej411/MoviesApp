package com.openplay.tech.myapplication.di

import com.openplay.tech.myapplication.commonutils.Constants
import com.openplay.tech.myapplication.data.remote.MovieApiService
import com.openplay.tech.myapplication.data.repositoryimpl.MoviesRepositoryImpl
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppNetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            level = HttpLoggingInterceptor.Level.BASIC
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(
                Interceptor { chain ->
                    val request: Request = chain.request().newBuilder()
                        .addHeader("accept", "application/json")
                        .addHeader(
                            "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9." +
                                    "eyJhdWQiOiI1NjBlZjU5MjZkNjY2Yzk3OTliZWVmOWQyODE2NDNjMyIsIm5iZiI6MTc" +
                                    "yNDQ0ODU4My4zODk0ODQsInN1YiI6IjY2YzhmM2E1NGZhODI1MTAzZGIzMDY1YSIsInNjb3Blcy" +
                                    "I6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nOs" +
                                    "-H5Zg7w6bSw7gWkzWKsM-5CIsxmXEODzPX72sBCY"
                        ).build()
                    chain.proceed(request)
                }
            ).addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieApiService: MovieApiService): MoviesRepository {
        return MoviesRepositoryImpl(movieApiService)
    }

}
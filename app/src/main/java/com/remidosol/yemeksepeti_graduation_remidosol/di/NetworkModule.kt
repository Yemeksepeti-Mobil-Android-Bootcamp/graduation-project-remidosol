package com.remidosol.yemeksepeti_graduation_remidosol.di

import android.content.Context
import android.util.Log
import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.remidosol.yemeksepeti_graduation_remidosol.data.remote.GraduationApiService
import com.remidosol.yemeksepeti_graduation_remidosol.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Inject
    lateinit var databaseModule: DatabaseModule

    @Provides
    fun provideApiService(retrofit: Retrofit): GraduationApiService {
        return retrofit.create(GraduationApiService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        @GraduationEndPoint endPoint: EndPoint
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val token =
                    databaseModule.sharedPrefManager(appContext)
                        .getToken()
                val request = it.request().newBuilder()
                if (token!!.isNotEmpty()) {
                    Log.v("RetrofitHelper", "Headers auth added.")
                    request
                        .addHeader("Authorization", "$token")
                }
                it.proceed(request.build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }).build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @GraduationEndPoint
    fun provideApiString(): EndPoint {
        return EndPoint("https://yemeksepeti-graduation-project.herokuapp.com/")
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: GraduationApiService,
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

}

data class EndPoint(val url: String)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GraduationEndPoint
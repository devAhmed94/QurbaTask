package com.example.firstcomposeapp.data.network

import com.example.firstcomposeapp.BuildConfig.DEBUG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 14/03/2022
 */

object RestClient {

  private const val BASE_URL = "url"

  val api: ApiService by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .client(httpClient)
      .build().create(ApiService::class.java)
  }

  private val httpClient by lazy {
    OkHttpClient.Builder()
      .addInterceptor(headerInterceptor())
      .addInterceptor(loggingInterceptor())
      .build()
  }

  private fun headerInterceptor(): Interceptor {
    return Interceptor {
      val original = it.request()
      val auth = ""

      val request = original.newBuilder()
        .header("Accept", "application/json")
        .header("Platform", "android")
        .header("FbToken", "")
        .header("Lang", "")
        .header("Authorization", auth)
        .header("CountryID", "")
        .build()

      it.proceed(request)
    }
  }

  private fun loggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
  }
}
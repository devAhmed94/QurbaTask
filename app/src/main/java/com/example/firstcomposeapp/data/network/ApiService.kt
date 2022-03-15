package com.example.firstcomposeapp.data.network

import com.example.firstcomposeapp.domain.Post
import retrofit2.Response
import retrofit2.http.GET

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 14/03/2022
 */
interface ApiService {

  @GET("home")
  suspend fun getHome(): Response<List<Post?>>
}
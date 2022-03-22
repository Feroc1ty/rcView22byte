package ru.rykunov.rcview22byte.retrofit

import retrofit2.Call
import retrofit2.http.GET
import ru.rykunov.rcview22byte.pojo.NewsList

interface NewsApi {

    @GET("top-headlines?country=us&category=technology&apiKey=97b8bec2656546d2a16aa3e64865a3b3")
    fun getNewsList(): Call<NewsList>
}
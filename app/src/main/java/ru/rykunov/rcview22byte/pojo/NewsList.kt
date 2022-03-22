package ru.rykunov.rcview22byte.pojo

data class NewsList(
    val articles: List<News>,
    val status: String,
    val totalResults: Int
)
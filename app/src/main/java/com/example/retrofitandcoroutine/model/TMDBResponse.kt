package com.example.retrofitandcoroutine.model

data class TMDBResponse<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)
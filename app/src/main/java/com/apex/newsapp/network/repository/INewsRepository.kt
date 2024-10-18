package com.apex.newsapp.network.repository

import androidx.lifecycle.LiveData
import com.apex.newsapp.data.model.NewsArticle
import com.apex.newsapp.data.model.NewsResponse
import com.apex.newsapp.state.NetworkState

interface INewsRepository {
    suspend fun getNews(countryCode: String, pageNumber: Int): NetworkState<NewsResponse>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): NetworkState<NewsResponse>

    suspend fun saveNews(news: NewsArticle): Long

    fun getSavedNews(): LiveData<List<NewsArticle>>

    suspend fun deleteNews(news: NewsArticle)

    suspend fun deleteAllNews()
}

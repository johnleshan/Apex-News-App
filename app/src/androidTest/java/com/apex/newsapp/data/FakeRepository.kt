package com.apex.newsapp.data

import FakeDataUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apex.newsapp.data.model.NewsArticle
import com.apex.newsapp.data.model.NewsResponse
import com.apex.newsapp.network.repository.INewsRepository
import com.apex.newsapp.state.NetworkState

class FakeRepository : INewsRepository {

    private val observableNewsArticle = MutableLiveData<List<NewsArticle>>()

    override suspend fun getNews(
        countryCode: String,
        pageNumber: Int
    ): NetworkState<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNews(
        searchQuery: String,
        pageNumber: Int
    ): NetworkState<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(news: NewsArticle): Long {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): LiveData<List<NewsArticle>> {
        return FakeDataUtil.getFakeNewsArticleLiveData()
    }

    override suspend fun deleteNews(news: NewsArticle) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNews() {
        TODO("Not yet implemented")
    }
}
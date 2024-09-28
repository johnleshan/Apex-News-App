package com.poc.newsreader2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private static String BASE_URL = "https://newsapi.org/v2/";
    private static String API_KEY = "b15243adaa9d417bbd6fc72ed36af938";

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private ArrayList<NewsArticle> mArticleList;
    private ArticleAdapter mArticleAdapter;

    private NewsApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressbar_id);
        mRecyclerView = findViewById(R.id.recyclerview_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mArticleList = new ArrayList<>();

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())  // Using Gson for JSON parsing
                .build();

        // Create an instance of the API service
        apiService = retrofit.create(NewsApiService.class);

        // Fetch news articles
        fetchNewsFromApi();
    }

    private void fetchNewsFromApi() {
        mProgressBar.setVisibility(View.VISIBLE);

        Call<NewsResponse> call = apiService.getTopHeadlines("in", API_KEY);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<NewsArticle> articles = response.body().getArticles();
                    mArticleList.addAll(articles);

                    // Set adapter
                    mArticleAdapter = new ArticleAdapter(getApplicationContext(), mArticleList);
                    mRecyclerView.setAdapter(mArticleAdapter);
                } else {
                    Log.e(TAG, "Failed to retrieve news: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Log.e(TAG, "Error fetching news: " + t.getMessage());
            }
        });
    }
}

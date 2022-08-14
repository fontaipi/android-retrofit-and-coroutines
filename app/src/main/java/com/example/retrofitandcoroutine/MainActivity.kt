package com.example.retrofitandcoroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.retrofitandcoroutine.model.Movie
import com.example.retrofitandcoroutine.service.ApiClient
import com.example.retrofitandcoroutine.service.TheMovieDbService
import com.example.retrofitandcoroutine.ui.theme.RetrofitAndCoroutineTheme
import retrofit2.Retrofit
import kotlinx.coroutines.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var movieListResponse: List<Movie> by mutableStateOf(listOf())


        setContent {
            RetrofitAndCoroutineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column {
                        Text(text = "Movies : ")
                        Button(
                            onClick = {
                                lifecycleScope.launch {
                                    try {
                                        val movieList = ApiClient.apiService.topMovies()
                                        movieListResponse = movieList.results
                                    } catch (e: Exception) {
                                        Log.d("ERROR", e.message!!)
                                    }
                                }
                            },
                        ) {
                            Text("load")
                        }
                        LazyColumn {
                            items(movieListResponse) {
                                Text(text = it.title)
                            }
                        }
                    }

                }
            }
        }
    }
}
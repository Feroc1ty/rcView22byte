package ru.rykunov.rcview22byte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ru.rykunov.rcview22byte.adapter.NewsAdapter
import ru.rykunov.rcview22byte.databinding.ActivityMainBinding
import ru.rykunov.rcview22byte.pojo.News
import ru.rykunov.rcview22byte.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var homeMvvm: HomeViewModel
    lateinit var newsItemsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        newsItemsAdapter = NewsAdapter(this)
        setContentView(binding.root)

        prepareNewsRcView()
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]
        homeMvvm.getNewsList()
        observeNewsItemsLiveData()


    }

    /*
    Подготовка Recycler View
    */
    private fun prepareNewsRcView() {
        binding.rcNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = newsItemsAdapter
        }
    }

    private fun observeNewsItemsLiveData() {
        homeMvvm.observeNewsLiveData().observe(this
        ) { newsList ->
            newsItemsAdapter.setNews(newslistarray = newsList as ArrayList<News>)
        }

    }
}
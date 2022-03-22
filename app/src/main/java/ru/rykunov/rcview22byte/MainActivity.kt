package ru.rykunov.rcview22byte

import android.content.Intent
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
        onNewsItemClick()


    }

    /*
    Слушатель нажатий в RecyclerView и передача данных в новое акативити через интент
     */
    private fun onNewsItemClick() {
        newsItemsAdapter.onItemClick = { news ->
            val intent = Intent(this, NewsDetailsActivity::class.java)
            intent.putExtra(NEWS_TITLE, news.title)
            intent.putExtra(NEWS_IMG, news.urlToImage)
            intent.putExtra(NEWS_DETAILS, news.description)
            intent.putExtra(NEWS_DATE, news.publishedAt)
            intent.putExtra(NEWS_URL, news.url)
            intent.putExtra(NEWS_SOURCE, news.source.name)
            startActivity(intent)
        }
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


    companion object{
        const val NEWS_TITLE = "titleNews"
        const val NEWS_IMG = "imgNews"
        const val NEWS_DETAILS = "detailsNews"
        const val NEWS_DATE = "dateNews"
        const val NEWS_URL = "urlNews"
        const val NEWS_SOURCE = "sourceNews"
    }
}
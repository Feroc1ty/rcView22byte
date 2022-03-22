package ru.rykunov.rcview22byte.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.rykunov.rcview22byte.MainActivity
import ru.rykunov.rcview22byte.databinding.NewsItemsBinding
import ru.rykunov.rcview22byte.pojo.News
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(mainActivity: MainActivity) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsList = ArrayList<News>()
    lateinit var onItemClick: ((News) -> Unit)

    fun setNews(newslistarray: ArrayList<News>){
        this.newsList = newslistarray
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(newsList[position].urlToImage)
            .into(holder.binding.imAvatar)
        holder.binding.tvTitle.setText(newsList[position].title)

        val dtStart = newsList[position].publishedAt
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date: Date = format.parse(dtStart)

        holder.binding.tvDate.setText(date.toString())

        holder.binding.newsCard.setOnClickListener {
            onItemClick.invoke(newsList[position])
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsViewHolder(val binding: NewsItemsBinding): RecyclerView.ViewHolder(binding.root)
}
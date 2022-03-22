package ru.rykunov.rcview22byte.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.rykunov.rcview22byte.DateFormatter
import ru.rykunov.rcview22byte.MainActivity
import ru.rykunov.rcview22byte.R
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

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (newsList[position].urlToImage != null){
            Glide.with(holder.itemView)
                .load(newsList[position].urlToImage)
                .into(holder.binding.imAvatar)
        }
        else holder.binding.imAvatar.setImageResource(R.drawable.img)
        holder.binding.tvTitle.setText(newsList[position].title)
        holder.binding.tvDate.setText(DateFormatter.getDate(newsList[position].publishedAt))
        holder.binding.tvSource.setText(newsList[position].source.name)

        holder.binding.newsCard.setOnClickListener {
            onItemClick.invoke(newsList[position])
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsViewHolder(val binding: NewsItemsBinding): RecyclerView.ViewHolder(binding.root)
}
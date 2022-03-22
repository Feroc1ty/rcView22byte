package ru.rykunov.rcview22byte.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rykunov.rcview22byte.pojo.News
import ru.rykunov.rcview22byte.pojo.NewsList
import ru.rykunov.rcview22byte.retrofit.RetrofitInstance

class HomeViewModel(): ViewModel() {

    private var newsListLiveData = MutableLiveData<List<News>>()

    fun getNewsList(){
        RetrofitInstance.api.getNewsList().enqueue(object : Callback<NewsList> {
            override fun onResponse(call: Call<NewsList>, response: Response<NewsList>) {
                if (response.body() != null){
                    newsListLiveData.value = response.body()!!.articles
                }
            }

            override fun onFailure(call: Call<NewsList>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
            }
        })
    }


    fun observeNewsLiveData(): LiveData<List<News>> {
        return newsListLiveData
    }

}
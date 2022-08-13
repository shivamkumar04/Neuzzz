package android.example.neuzzz.recyclerview

import android.example.neuzzz.R
import android.example.neuzzz.retrofit.News
import android.example.neuzzz.retrofit.NewsService
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adaptor: MyAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }

    private fun getNews() {
        val news: Call<News> = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News> {

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("yoyo", "Error in fetching news")

            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? = response.body()
                if (news != null) {
                    Log.d("yoyo", news.toString())


                    adaptor= MyAdaptor(this@MainActivity,news.articles)
                    NewsList.adapter=adaptor
                    NewsList.layoutManager=LinearLayoutManager(this@MainActivity)

                }
            }
        })

    }
}
package android.example.neuzzz.recyclerview

import android.content.Context
import android.content.Intent
import android.example.neuzzz.R
import android.example.neuzzz.retrofit.Articles
import android.graphics.Color
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdaptor(val context : Context, private val articles :List<Articles>) : RecyclerView.Adapter<MyAdaptor.ArticleViewHolder>(){

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var newsImage = itemView.findViewById<ImageView>(R.id.NewsImage)
        var newsTitle =itemView.findViewById<TextView>(R.id.txtTitle)
        var newsDescription =itemView.findViewById<TextView>(R.id.txtDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder
    {
        val inflater : LayoutInflater=LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int)
    {
        val article:Articles=articles[position]
        holder.newsTitle.text=article.title
        holder.newsDescription.text=article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

        holder.itemView.setOnClickListener{
            Toast.makeText(context,article.title,Toast.LENGTH_SHORT).show()
            val intent =Intent(context,DetailActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int
    {
       return articles.size
    }

}

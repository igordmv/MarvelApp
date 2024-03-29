package com.mobile.mavelapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mavelapp.R
import com.mobile.mavelapp.model.Result
import com.mobile.mavelapp.view.detail.HeroDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_hero.view.*

class CharactersListAdapter(val context : Context, val results : ArrayList<Result>) : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_hero, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentResult = results!!.get(position)
        holder.let {
            it.bindView(currentResult)
        }
    }
    fun addItem(reult: Result) {

        results.add(reult)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val name = itemView.tvHeroNameList
        val image = itemView.ivHeroList

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(views: View?) {

            when(views){
                itemView ->{
                    val intent = Intent(context, HeroDetailActivity::class.java)
                    intent.putExtra("heroId",results.get(adapterPosition).id.toString())
                    intent.putExtra("name",results.get(adapterPosition).name)
                    context.startActivity(intent)
                }
            }


        }
        fun bindView(result: Result){
            name.text = result.name
            Picasso.with(context).load(result.thumbnail?.path + "/" + "landscape_amazing." + result.thumbnail?.extension)
                .into(image)
        }
    }
}
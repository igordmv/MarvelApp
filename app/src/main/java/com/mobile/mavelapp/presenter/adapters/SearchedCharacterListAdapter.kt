package com.mobile.mavelapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mavelapp.R
import com.mobile.mavelapp.model.Result
import com.mobile.mavelapp.view.detail.HeroDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_hero.view.*

class SearchedCharacterListAdapter(val context : Context, val results : ArrayList<Result>) : RecyclerView.Adapter<SearchedCharacterListAdapter.ViewHolder>(), Filterable {
    var searchResultHeroList: MutableList<Result>
    init {
        searchResultHeroList = results
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    searchResultHeroList = results
                } else {
                    val filteredList = ArrayList<Result>()
                    for (result in results) {
                        if (result.name!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(result)
                        }
                    }
                    searchResultHeroList = filteredList
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = searchResultHeroList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                searchResultHeroList = filterResults.values as ArrayList<Result>
                notifyDataSetChanged()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.search_view_hero, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchResultHeroList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentResult = searchResultHeroList!!.get(position)
        holder.let {
            it.bindView(currentResult)
        }
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
                    intent.putExtra("heroId",searchResultHeroList.get(adapterPosition).id.toString())
                    intent.putExtra("name",searchResultHeroList.get(adapterPosition).name)
                    context.startActivity(intent)
                }
            }


        }
        fun bindView(result: Result){
            name.text = result.name
            Picasso.with(context).load(result.thumbnail?.path + "/" + "standard_medium." + result.thumbnail?.extension)
                .into(image)
        }
    }
}
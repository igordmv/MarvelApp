package com.mobile.mavelapp.presenter.adapters.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mavelapp.R
import com.mobile.mavelapp.model.DetailResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_events.view.*

class HeroDetailedEventsAdapter(val context : Context, val results : ArrayList<DetailResult>) : RecyclerView.Adapter<HeroDetailedEventsAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_events, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentResult = results.get(position)
        holder.let {
            it.bindView(currentResult)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        override fun onClick(views: View?) {

        }
        fun bindView(result: DetailResult){
            val image = itemView.detailed_events_image_id
            val text = itemView.detailed_events_title_id
            text.text = result.title

            Picasso.with(context).load(result.thumbnail?.path + "/" + "landscape_incredible." + result.thumbnail?.extension)
                .into(image)
        }
    }
}
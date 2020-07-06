package com.ijikod.punkapp.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.punkapp.Model.Beer


class BeerAdapter (private val onClickListener : BeerOnclick) : PagedListAdapter<Beer, RecyclerView.ViewHolder>(BEER_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BeerViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val beer = getItem(position)
        if (beer != null){
            (holder as BeerViewHolder).bind(beer, onClickListener)
        }
    }


    companion object {
        private val BEER_COMPARATOR = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(old: Beer, new: Beer): Boolean =
                old.id == new.id

            override fun areContentsTheSame(old: Beer, new: Beer): Boolean =
                old == new
        }
    }

    interface BeerOnclick{
        fun bearClick(beer: Beer)
    }


}
package com.ijikod.punkapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.R

class BeerViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    val beerNameTxt = view.findViewById<TextView>(R.id.beer_name)
    val beerImg = view.findViewById<ImageView>(R.id.beer_img)
    val beerABVTxt = view.findViewById<TextView>(R.id.abv_value)


    fun bind(beer : Beer, onClickListener: BeerAdapter.BeerOnclick){
        beerNameTxt.text = beer.name
        Glide.with(beerImg.context).load(beer.imageURL).placeholder(R.drawable.craft_beer_clip).into(beerImg)
        beerABVTxt.text = String.format(beerABVTxt.context.resources.getString(R.string.abv_txt),  beer.abv.toString())

        view.setOnClickListener {
            onClickListener.bearClick(beer)
        }
    }


    companion object {
        fun create(parent: ViewGroup): BeerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.beer_list_item, parent, false)
            return BeerViewHolder(view)
        }
    }


}
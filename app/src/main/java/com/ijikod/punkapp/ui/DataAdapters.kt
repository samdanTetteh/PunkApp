package com.ijikod.punkapp.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.R



    /**
     *  A [BindingAdapter] that uses the Glide library to load the beer image.
     */
    @BindingAdapter("app:imageUrl")
    fun loadImage(imageView: ImageView, image : String){
        Glide.with(imageView.context).load(image).placeholder(R.drawable.craft_beer_clip).into(imageView)
    }


    @BindingAdapter("abv")
    fun setAbv(textView: TextView, abv: String){
        textView.text =  String.format(textView.context.resources.getString(R.string.abv_txt), abv)
    }



    @BindingAdapter( "malt")
    fun setValues(textView: TextView, beer: Beer){
        val maltIngredients =  beer.ingredients?.malt?.joinToString {
                it.name.toString()
        }

            val hops =  beer.ingredients?.hops?.joinToString {
                it.name.toString()
            }

            val foodPairings = beer.foodPairings?.joinToString {
                it
            }

        textView.text = textView.context.getString(R.string.detail_info_txt, maltIngredients, hops, foodPairings)
    }



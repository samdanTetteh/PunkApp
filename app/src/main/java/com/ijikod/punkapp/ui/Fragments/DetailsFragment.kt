package com.ijikod.punkapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ijikod.punkapp.App.PunkApplication
import com.ijikod.punkapp.R
import com.ijikod.punkapp.databinding.FragmentDetailsBinding
import com.ijikod.punkapp.ui.Activities.Inspector
import com.ijikod.punkapp.ui.BeersListViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment(val inspector: Inspector) : Fragment() {

    lateinit var viewModel : BeersListViewModel

    lateinit var detailsImg : ImageView
    lateinit var name : TextView
    lateinit var year : TextView
    lateinit var abv : TextView
    lateinit var infoTxt : TextView
    lateinit var descTxt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, PunkApplication.provideViewModelFactory(requireContext()))
            .get(BeersListViewModel::class.java)



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =   FragmentDetailsBinding.inflate(inflater, container, false)

        val beer = arguments?.let { DetailsFragmentArgs.fromBundle(it).beer }

        setHasOptionsMenu(true)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.selectedBeer.value = beer



        //TODO : Implement new databinding here
//        detailsImg = binding.detailsBearImage
//        name = binding.ipaTxt
//        year = binding.yearTxt
//        abv = binding.abvTxt
//        descTxt = binding.descriptionTxt
//        infoTxt = binding.infoTxt

//        beer?.let {
//            Glide.with(requireActivity()).load(it.imageURL).placeholder(R.drawable.craft_beer_clip).into(detailsImg)
//            name.text = it.name
//            year.text = it.firstBrewed
//            abv.text = String.format(requireActivity().resources.getString(R.string.abv_txt), it.abv)
//            descTxt.text = it.description
//
//            val maltIngredients =  it.ingredients?.malt?.joinToString {
//                it.name.toString()
//            }
//
//            val hops =  it.ingredients?.hops?.joinToString {
//                it.name.toString()
//            }
//
//            val foodPairings = it.foodPairings?.joinToString {
//                it
//            }
//
//
//            infoTxt.text = this.getString(R.string.detail_info_txt, maltIngredients, hops, foodPairings)
//
//        }


        beer?.name?.let { inspector.setTitle(it) }
        inspector.setActionBarUpVisibility(true)

        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home){
           inspector.getNavigation().navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }

}

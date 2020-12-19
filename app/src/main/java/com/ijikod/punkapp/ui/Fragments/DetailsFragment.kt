package com.ijikod.punkapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.ijikod.punkapp.App.PunkApplication
import com.ijikod.punkapp.databinding.FragmentDetailsBinding
import com.ijikod.punkapp.ui.BeersListViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment() : Fragment() {

    lateinit var viewModel: BeersListViewModel

    lateinit var name: TextView
    lateinit var abv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, PunkApplication.provideViewModelFactory(requireContext()))
                .get(BeersListViewModel::class.java)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val beer = arguments?.let { DetailsFragmentArgs.fromBundle(it).beer }

        setHasOptionsMenu(true)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.selectedBeer.value = beer

        return binding.root
    }

}

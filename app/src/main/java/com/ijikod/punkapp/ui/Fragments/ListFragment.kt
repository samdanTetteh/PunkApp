package com.ijikod.punkapp.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ijikod.punkapp.App.PunkApplication
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.R
import com.ijikod.punkapp.ui.Activities.Inspector
import com.ijikod.punkapp.ui.Activities.MainActivity
import com.ijikod.punkapp.ui.BeerAdapter
import com.ijikod.punkapp.ui.BeerAdapter.BeerOnclick
import com.ijikod.punkapp.ui.BeersListViewModel

/**
 * A simple [Fragment] subclass.
 */
class ListFragment(val inspector : Inspector) : Fragment() {

    private lateinit var viewModel : BeersListViewModel
    private lateinit var adapter : BeerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText : TextView
    private lateinit var refreshView: SwipeRefreshLayout

     lateinit var listener : BeerOnclick


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, PunkApplication.provideViewModelFactory(requireContext()))
            .get(BeersListViewModel::class.java)

        viewModel.getNewBeers(false)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  =  inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.list)
        emptyText = view.findViewById(R.id.empty_txt)
        refreshView = view.findViewById(R.id.pull_refresh)



        inspector.setTitle(getString(R.string.punk_beers_txt))
        inspector.setActionBarUpVisibility(false)


        initAdapter()
        initPullDownRefresh()
        return view
    }


     fun initAdapter() {
        listener = object : BeerOnclick {
            override fun bearClick(beer: Beer) {
                val action = ListFragmentDirections.actionListFragmentToDetailsFragment(beer)
                inspector.getNavigation().navigate(action)
            }
        }

        adapter = BeerAdapter(this.listener)
        recyclerView.adapter = adapter


        viewModel.beers.observe(requireActivity(), Observer<PagedList<Beer>> {
            Log.d("Activity", "list: ${it.size}")
            if (refreshView.isRefreshing) refreshView.isRefreshing = false
            showEmptyList(it.isEmpty())
            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(requireActivity(), Observer<String> {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_LONG).show()
        })


    }


    private fun initPullDownRefresh(){
        refreshView.setOnRefreshListener{
            viewModel.getNewBeers(true)
        }
    }



    private fun showEmptyList(showList : Boolean){
        if(!showList){
          recyclerView.visibility = View.VISIBLE
            emptyText.visibility = View.GONE
        }else{
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
        }
    }


}

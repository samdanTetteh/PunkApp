package com.ijikod.punkapp.ui.Fragments

import androidx.fragment.app.FragmentFactory
import com.ijikod.punkapp.ui.Activities.Inspector

class ListFragmentFactory(val inspector: Inspector) : FragmentFactory() {
    override fun instantiate(
        classLoader: ClassLoader,
        className: String
    ) = when (className) {
        ListFragment::class.java.name -> ListFragment(inspector)
        DetailsFragment::class.java.name -> DetailsFragment(inspector)
        else -> super.instantiate(classLoader, className)
    }
}
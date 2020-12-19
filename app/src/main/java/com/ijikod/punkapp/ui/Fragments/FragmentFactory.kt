package com.ijikod.punkapp.ui.Fragments

import androidx.fragment.app.FragmentFactory

class FragmentFactory() : FragmentFactory() {
    override fun instantiate(
        classLoader: ClassLoader,
        className: String
    ) = when (className) {
        ListFragment::class.java.name -> ListFragment()
        DetailsFragment::class.java.name -> DetailsFragment()
        else -> super.instantiate(classLoader, className)
    }
}
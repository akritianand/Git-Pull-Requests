package com.akriti.meeshoapp.dependencies

import com.akriti.meeshoapp.view.injection.fragment.PRListFragmentBindings
import com.akriti.meeshoapp.view.injection.fragment.SearchFragmentBindings
import dagger.Module

@Module(includes = [
    SearchFragmentBindings::class,
    PRListFragmentBindings::class
])
class FragmentBindings
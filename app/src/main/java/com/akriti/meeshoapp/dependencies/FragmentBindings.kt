package com.akriti.meeshoapp.dependencies

import com.akriti.meeshoapp.view.injection.fragment.PRListFragmentBuilder
import com.akriti.meeshoapp.view.injection.fragment.SearchFragmentBuilder
import dagger.Module

@Module(includes = [
    SearchFragmentBuilder::class,
    PRListFragmentBuilder::class
])
class FragmentBindings
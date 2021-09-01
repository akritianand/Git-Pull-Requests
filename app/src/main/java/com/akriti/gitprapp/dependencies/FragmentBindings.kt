package com.akriti.gitprapp.dependencies

import com.akriti.gitprapp.view.injection.fragment.PRListFragmentBuilder
import com.akriti.gitprapp.view.injection.fragment.SearchFragmentBuilder
import dagger.Module

@Module(includes = [
    SearchFragmentBuilder::class,
    PRListFragmentBuilder::class
])
class FragmentBindings
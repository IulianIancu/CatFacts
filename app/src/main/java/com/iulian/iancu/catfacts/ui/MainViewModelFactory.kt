package com.iulian.iancu.catfacts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iulian.iancu.catfacts.data.CatFactsRepository
import com.iulian.iancu.catfacts.data.CatImageRepository
import com.iulian.iancu.catfacts.ui.main.MainViewModel

class MainViewModelFactory(
    private val catFactsRepository: CatFactsRepository,
    private val catImageRepository: CatImageRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(catFactsRepository, catImageRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
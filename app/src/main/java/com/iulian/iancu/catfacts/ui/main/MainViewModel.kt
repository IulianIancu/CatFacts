package com.iulian.iancu.catfacts.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iulian.iancu.catfacts.data.CatFactsRepository
import com.iulian.iancu.catfacts.data.CatImageRepository
import kotlinx.coroutines.*

class MainViewModel constructor(
    private val catFactsRepository: CatFactsRepository,
    private val catImageRepository: CatImageRepository
) : ViewModel() {
    private val _state = MutableLiveData(State(null, null))
    val state: LiveData<State> get() = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.postValue(_state.value?.copy(error = Error.Unknown))
    }

    private var job: Job? = null

    fun getCatFacts() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = catFactsRepository.getCatFacts(NUMBER_OF_CAT_FACTS)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    val catFacts = response.body()?.map {
                        catImageRepository.getCatImageURL() to it.text
                    }
                    _state.postValue(
                        _state.value?.copy(
                            error = null,
                            catList = catFacts
                        )
                    )
                } else {
                    _state.postValue(_state.value?.copy(error = Error.Network))
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    companion object {
        const val NUMBER_OF_CAT_FACTS = 10
    }
}


data class State(
    val error: Error?,
    val catList: List<Pair<String, String>>?
)

sealed class Error() {
    object Network : Error()
    object Unknown : Error()
}
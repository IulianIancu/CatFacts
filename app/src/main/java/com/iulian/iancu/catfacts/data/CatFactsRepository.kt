package com.iulian.iancu.catfacts.data

class CatFactsRepository constructor(private val retrofitService: CatService) {
    suspend fun getCatFacts(amount: Int) =
        retrofitService.getCatFacts(amount)
}

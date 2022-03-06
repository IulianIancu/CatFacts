package com.iulian.iancu.catfacts.data

class CatImageRepository {
    companion object{
        val catImages = listOf(
            "https://media.giphy.com/media/ICOgUNjpvO0PC/giphy.gif",
            "https://media.giphy.com/media/BzyTuYCmvSORqs1ABM/giphy.gif",
            "https://media.giphy.com/media/3o6Zt481isNVuQI1l6/giphy.gif",
            "https://media.giphy.com/media/MDJ9IbxxvDUQM/giphy.gif",
            "https://media.giphy.com/media/mlvseq9yvZhba/giphy.gif",
            "https://media.giphy.com/media/v6aOjy0Qo1fIA/giphy.gif",
            "https://media.giphy.com/media/12bjQ7uASAaCKk/giphy.gif",
            "https://media.giphy.com/media/lJNoBCvQYp7nq/giphy.gif",
            "https://media.giphy.com/media/3oEduQAsYcJKQH2XsI/giphy.gif",
            "https://media.giphy.com/media/yFQ0ywscgobJK/giphy.gif",
            "https://media.giphy.com/media/DPiuat0EsqP3a/giphy.gif",
            "https://media.giphy.com/media/ZE6HYckyroMWwSp11C/giphy.gif",
            "https://media.giphy.com/media/CjmvTCZf2U3p09Cn0h/giphy.gif"
        )
    }

    fun getCatImageURL():String{
        //Maybe use a real service for this at some point
        return catImages.random()
    }
}
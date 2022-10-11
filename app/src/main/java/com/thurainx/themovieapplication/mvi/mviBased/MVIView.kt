package com.thurainx.themovieapplication.mvi.mviBased

interface MVIView<S : MVIState>{
    fun render(state: S)
}
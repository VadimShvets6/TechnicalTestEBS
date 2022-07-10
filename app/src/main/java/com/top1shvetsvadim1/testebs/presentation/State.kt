package com.top1shvetsvadim1.testebs.presentation

sealed class State()

class Loading(val isLoading : Boolean = false) : State()
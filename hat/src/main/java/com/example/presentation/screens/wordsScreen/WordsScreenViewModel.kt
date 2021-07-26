package com.example.presentation.screens.wordsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.PlayerInteractor
import com.example.domain.interactor.WordInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class WordsScreenViewModel @Inject constructor(
    private val playerInteractor: PlayerInteractor,
    private val wordInteractor: WordInteractor
) : ViewModel() {

    private var wordsState: WordsScreenState = WordsScreenState()
    val stateFlow = MutableStateFlow(WordsScreenState())
//    val commandFlow = MutableSharedFlow<AddPlayerCommand>()

    init {
        combine(
            playerInteractor.state,
            wordInteractor.wordsRestrictionObserver
        ) { players, wordsRestriction ->
            wordsState = wordsState.copy(players = players, wordsRestriction = wordsRestriction)
            stateFlow.emit(wordsState)
        }.launchIn(viewModelScope)
    }

   fun setRestriction(count: Int) {
       wordInteractor.wordsRestriction = count
   }

}
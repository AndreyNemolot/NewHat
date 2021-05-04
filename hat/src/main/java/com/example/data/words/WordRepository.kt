package com.example.data.words

import com.example.domain.model.Word
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class WordRepository @Inject constructor() {
    private val wordSet: MutableSet<Word> = mutableSetOf()

    init {
        println("ASDASD")
    }
    private val channel: MutableStateFlow<List<Word>> = MutableStateFlow(emptyList())
    val state: Flow<List<Word>> = channel

    fun getWord(): List<Word> {
        return wordSet.toList()
    }

    fun addWord(WordName: Word) {
        wordSet.add(WordName)
        channel.tryEmit(wordSet.toList())
    }

    fun removeWord(WordName: Word) {
        wordSet.remove(WordName)
        channel.tryEmit(wordSet.toList())
    }

    fun getWordSize(): Int {
        return wordSet.size
    }
}

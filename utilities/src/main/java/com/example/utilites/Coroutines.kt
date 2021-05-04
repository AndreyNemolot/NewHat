package com.example.utilites

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import java.util.concurrent.Flow

//fun <T> Flow<T>.observeOn(lifecycleScope: LifecycleCoroutineScope, block: (T) -> Unit) {
//    lifecycleScope.launchWhenCreated {
//        collect {
//            block(it)
//        }
//    }
//}
//
//fun <T> Flow<T>.observeOn(viewLifecycleOwner: LifecycleOwner, block: (T) -> Unit) {
//    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//        collect {
//            block(it)
//        }
//    }
//}
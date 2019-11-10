package com.urban.noise.library.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified VM : ViewModel> Fragment.viewModel(
    key: String? = null,
    noinline factory: () -> VM
): VM {
    @Suppress("UNCHECKED_CAST")
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = factory() as T
    }

    return if (key != null) {
        ViewModelProviders.of(this, viewModelProviderFactory).get(key, VM::class.java)
    } else {
        ViewModelProviders.of(this, viewModelProviderFactory).get(VM::class.java)
    }
}

/*
 * Copyright 2019 urbannoise.org
 *
 * Licensed under the Attribution-NonCommercial-ShareAlike 4.0
 * International (CC BY-NC-SA 4.0) you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.urban.noise.base.android.extensions

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

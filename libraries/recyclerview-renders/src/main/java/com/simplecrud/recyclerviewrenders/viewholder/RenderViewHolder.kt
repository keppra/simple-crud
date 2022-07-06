/*
 * Copyright (C) 2014 Alejandro Rodriguez Salamanca.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simplecrud.recyclerviewrenders.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.simplecrud.recyclerviewrenders.interfaces.Renderable

/**
 * @param <R> Renderable that will be received in #onBindViewHolder
 * @author Alejandro Rodriguez <https:></https:>//github.com/Alexrs95>
 */
abstract class RenderViewHolder<R : Renderable>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val context: Context = itemView.context

    var item: R? = null

    abstract fun onBindView(item: R)

    open fun onViewRecycled() {}

    open fun onViewAttachedToWindow() {}

    open fun onViewDetachedFromWindow() {}

    open fun onDetachedFromRecyclerView() {}

}

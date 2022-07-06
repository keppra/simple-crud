package com.simplecrud.recyclerviewrenders.adapter
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

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplecrud.recyclerviewrenders.builder.RendererBuilder
import com.simplecrud.recyclerviewrenders.interfaces.Renderable
import com.simplecrud.recyclerviewrenders.viewholder.RenderViewHolder

/**
 * @param items   List that contains the items to show
 * @param builder Builder that create the Renderers
 * The type Renderer adapter.
 */
open class RendererAdapter<R : Renderable>(
    var items: MutableList<R>?,
    private val builder: RendererBuilder<R>?
) :
    RecyclerView.Adapter<RenderViewHolder<R>>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RenderViewHolder<R> {
        val renderer = builder?.instantiate(viewType)?.create()
        return renderer!!.onCreateViewHolder(viewGroup, viewType) as RenderViewHolder<R>
    }

    override fun onBindViewHolder(holder: RenderViewHolder<R>, position: Int) {
        items?.let {
            if (it.isNotEmpty() && position < it.size) {
                holder.item = it[position]
                holder.onBindView(it[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int = items?.get(position)?.renderableId ?: 0

    override fun getItemId(position: Int): Long = super.getItemId(position)

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onViewRecycled(holder: RenderViewHolder<R>) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }

    override fun onViewAttachedToWindow(holder: RenderViewHolder<R>) {
        super.onViewAttachedToWindow(holder)
        holder.onViewAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: RenderViewHolder<R>) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
    }

    fun add(item: R, position: Int) {
        items?.add(position, item)
        notifyItemInserted(position)
    }

    fun clear() {
        items?.clear()
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        items?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateRenderable(render: R, position: Int) {
        items?.removeAt(position)
        items?.add(position, render)
        notifyItemChanged(position)
    }

    fun update(items: MutableList<R>) {
        this.items = items
        notifyDataSetChanged()
    }

    protected fun getItem(position: Int): Renderable? =
        try {
            items?.get(position)
        } catch (e: IndexOutOfBoundsException) {
            null
        }
}
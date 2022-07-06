package com.simplecrud.recyclerviewrenders.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simplecrud.recyclerviewrenders.interfaces.Renderable
import com.simplecrud.recyclerviewrenders.viewholder.RenderViewHolder

/**
 *
 * @param layout id
 * @author Alejandro Rodriguez <https:></https:>//github.com/Alexrs95>
 */
abstract class Renderer<R : Renderable>(val id: Int) {

    /**
     * Creates the ViewHolder of the Renderer
     *
     * @param viewGroup the container
     * @return an instance of the ViewHolder
     */
    abstract fun onCreateViewHolder(viewGroup: ViewGroup, id: Int): RenderViewHolder<*>

    /**
     * Inflates the view that corresponds to the id.
     *
     * @param viewGroup the view group
     * @return a view
     */
    fun getView(viewGroup: ViewGroup): View {
        return LayoutInflater.from(viewGroup.context).inflate(id, viewGroup, false)
    }
}

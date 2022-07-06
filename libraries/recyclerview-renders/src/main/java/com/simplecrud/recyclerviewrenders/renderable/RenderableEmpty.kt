package com.simplecrud.recyclerviewrenders.renderable

import android.view.View
import android.view.ViewGroup
import com.simplecrud.recyclerviewrenders.interfaces.RenderableDiff
import com.simplecrud.recyclerviewrenders.renderer.Renderer
import com.simplecrud.recyclerviewrenders.viewholder.RenderViewHolder
import com.simplecrud.recyclerviewrenders.R

class EmptyRow : RenderableDiff {
    override fun hash(): String = EmptyRow::hashCode.toString()
    override val renderableId:
            Int = R.layout.empty_row
}

class EmptyRenderer(id: Int) : Renderer<RenderableDiff>(id) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, id: Int): EmptyViewHolder =
        EmptyViewHolder(getView(viewGroup))
}

class EmptyViewHolder(val view: View) : RenderViewHolder<EmptyRow>(view) {
    override fun onBindView(item: EmptyRow) {
        //do nothing
    }
}
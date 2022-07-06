package com.simplecrud.listusers.presentation.renderables.renders

import android.view.View
import android.view.ViewGroup
import com.simplecrud.listusers.R
import com.simplecrud.recyclerviewrenders.interfaces.RenderableDiff
import com.simplecrud.recyclerviewrenders.renderer.Renderer
import com.simplecrud.recyclerviewrenders.viewholder.RenderViewHolder

class DescriptionRow(
    val id: String
): RenderableDiff {
    override fun hash(): String = id
    override val renderableId: Int = R.layout.renderable_list_users_description
}

class DescriptionRenderer(
    id: Int
): Renderer<RenderableDiff>(id) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, id: Int) =
        DescriptionViewHolder(getView(viewGroup))
}

class DescriptionViewHolder(
    view: View
): RenderViewHolder<DescriptionRow>(view) {
    override fun onBindView(item: DescriptionRow) {}
}
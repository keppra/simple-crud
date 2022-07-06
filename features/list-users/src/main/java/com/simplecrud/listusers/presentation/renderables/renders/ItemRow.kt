package com.simplecrud.listusers.presentation.renderables.renders

import android.view.View
import android.view.ViewGroup
import com.simplecrud.listusers.R
import com.simplecrud.listusers.databinding.RenderableListUsersItemBinding
import com.simplecrud.recyclerviewrenders.interfaces.RenderableDiff
import com.simplecrud.recyclerviewrenders.renderer.Renderer
import com.simplecrud.recyclerviewrenders.viewholder.RenderViewHolder

class ItemRow(
    val id: String,
    val name: String,
    val birthdate: String
): RenderableDiff {
    override fun hash(): String = id
    override val renderableId: Int = R.layout.renderable_list_users_item
}

class ItemRenderer(
    id: Int
): Renderer<RenderableDiff>(id) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, id: Int) =

        ItemViewHolder(getView(viewGroup))
}

class ItemViewHolder(
    view: View
): RenderViewHolder<ItemRow>(view) {
    private val binding = RenderableListUsersItemBinding.bind(view)
    override fun onBindView(item: ItemRow) {
        binding.tvName.text = item.name
        binding.tvBirthdate.text = item.birthdate
    }
}
package com.simplecrud.listusers.presentation.renderables.factories

import com.simplecrud.listusers.R
import com.simplecrud.listusers.presentation.renderables.renders.DescriptionRenderer
import com.simplecrud.listusers.presentation.renderables.renders.ItemRenderer
import com.simplecrud.recyclerviewrenders.interfaces.RenderableDiff
import com.simplecrud.recyclerviewrenders.interfaces.RendererFactory
import com.simplecrud.recyclerviewrenders.renderable.EmptyRenderer
import com.simplecrud.recyclerviewrenders.renderer.Renderer

class ListUsersFactory(): RendererFactory<RenderableDiff> {
    override fun getRenderer(id: Int): Renderer<RenderableDiff> =
        when (id) {
            R.layout.renderable_list_users_description -> DescriptionRenderer(id)
            R.layout.renderable_list_users_item -> ItemRenderer(id)
            else -> EmptyRenderer(id)
        }
}
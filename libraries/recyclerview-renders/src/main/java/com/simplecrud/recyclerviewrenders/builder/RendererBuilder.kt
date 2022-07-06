package com.simplecrud.recyclerviewrenders.builder

import com.simplecrud.recyclerviewrenders.interfaces.Builder
import com.simplecrud.recyclerviewrenders.interfaces.Renderable
import com.simplecrud.recyclerviewrenders.interfaces.RendererFactory
import com.simplecrud.recyclerviewrenders.renderer.Renderer

/**
 * @author Alejandro Rodrigiez <https:></https:>//github.com/Alexrs95>
 *
 * @param RendererFactory that provide the Renderer
 * Builder that return the Renderer to display
 */
class RendererBuilder<R : Renderable>(private val factory: RendererFactory<R>) : Builder<R> {

    //layout ID
    private var id: Int = 0

    override fun create(): Renderer<R> {
        return factory.getRenderer(id)
    }

    override fun instantiate(id: Int): Builder<R> {
        this.id = id
        return this
    }
}

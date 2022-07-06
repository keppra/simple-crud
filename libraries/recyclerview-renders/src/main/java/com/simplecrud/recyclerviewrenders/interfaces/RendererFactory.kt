package com.simplecrud.recyclerviewrenders.interfaces

import com.simplecrud.recyclerviewrenders.renderer.Renderer

/**
 * @author Alejandro Rodriguez <https:></https:>//github.com/Alexrs95>
 * Interface that defines the implementation of the factory that return the renderer
 */
interface RendererFactory<R : Renderable> {

    /**
     * @param id the ID of the layout
     * @return the Renderer assigned to the layout
     */
    fun getRenderer(id: Int): Renderer<R>
}

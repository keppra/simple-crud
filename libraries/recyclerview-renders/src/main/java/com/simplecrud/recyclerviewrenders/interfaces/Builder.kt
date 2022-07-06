package com.simplecrud.recyclerviewrenders.interfaces

import com.simplecrud.recyclerviewrenders.renderer.Renderer

/**
 * @author Alejandro Rodriguez <https:></https:>//github.com/Alexrs95>
 */
interface Builder<R : Renderable> {
    /**
     * @param id the ID of the layout
     * @return an instance of the builder
     */
    fun instantiate(id: Int): Builder<R>

    /**
     * @return the Renderer assigned to the layout
     */
    fun create(): Renderer<R>
}

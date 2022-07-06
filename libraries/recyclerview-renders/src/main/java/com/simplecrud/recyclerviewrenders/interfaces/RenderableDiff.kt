package com.simplecrud.recyclerviewrenders.interfaces

/**
 * @author Jordi
 */
interface RenderableDiff : Renderable {

    /**
     * @return hash with the renderable content
     */
    fun hash(): String

    //fun <R: RenderableDiff> hasChanged(item: R): Boolean
}

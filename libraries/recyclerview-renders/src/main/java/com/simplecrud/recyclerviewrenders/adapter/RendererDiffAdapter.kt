package com.simplecrud.recyclerviewrenders.adapter

import androidx.recyclerview.widget.DiffUtil
import com.simplecrud.recyclerviewrenders.builder.RendererBuilder
import com.simplecrud.recyclerviewrenders.interfaces.RenderableDiff
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable


/**
 * The type Renderer adapter.
 * @param items   List that contains the items to show
 * @param builder Builder that create the Renderers
 */
class RendererDiffAdapter(
    var renderables: MutableList<RenderableDiff>,
    rendererBuilder: RendererBuilder<RenderableDiff>
) : RendererAdapter<RenderableDiff>(renderables, rendererBuilder) {

    var disposable: Disposable? = null

    fun refresh(
        newRenderables: MutableList<RenderableDiff>
    ) {
        disposable = Single.fromCallable { parseItems(newRenderables) }
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { diff ->
                diff.dispatchUpdatesTo(this@RendererDiffAdapter)
            }
    }

    fun dispose() {
        disposable?.dispose()
    }

    private fun parseItems(newRenderables: MutableList<RenderableDiff>): DiffUtil.DiffResult {
        val diffCallback = RenderableDiffCallback(renderables, newRenderables)
        val diffResult = DiffUtil.calculateDiff(diffCallback, true)
        renderables.clear()
        renderables.addAll(newRenderables)
        return diffResult
    }

    class RenderableDiffCallback(
        private val currentRenderableList: List<RenderableDiff>,
        private val newRenderableList: List<RenderableDiff>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = currentRenderableList.size

        override fun getNewListSize(): Int = newRenderableList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            currentRenderableList[oldItemPosition].renderableId == newRenderableList[newItemPosition].renderableId

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            currentRenderableList[oldItemPosition].hash() == newRenderableList[newItemPosition].hash()
                    //&& newRenderableList[newItemPosition].hasChanged(currentRenderableList[oldItemPosition])
    }

}
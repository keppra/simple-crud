package com.simplecrud.recyclerviewrenders.interfaces

interface RenderableViewModelDiff<VM>: RenderableDiff {
    var viewModel: VM
}
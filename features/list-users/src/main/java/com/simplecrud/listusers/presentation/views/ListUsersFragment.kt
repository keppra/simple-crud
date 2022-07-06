package com.simplecrud.listusers.presentation.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplecrud.base.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.simplecrud.listusers.R
import com.simplecrud.listusers.databinding.FragmentListUsersBinding
import com.simplecrud.listusers.presentation.mappers.ListUsersMapperRenderables
import com.simplecrud.listusers.presentation.models.UsersPresentation
import com.simplecrud.listusers.presentation.renderables.factories.ListUsersFactory
import com.simplecrud.listusers.presentation.viewmodels.ListUsersViewModel
import com.simplecrud.listusers.presentation.viewstates.*
import com.simplecrud.recyclerviewrenders.adapter.RendererDiffAdapter
import com.simplecrud.recyclerviewrenders.builder.RendererBuilder
import com.simplecrud.recyclerviewrenders.interfaces.RenderableDiff
import com.simplecrud.utils.extensions.TAG
import org.koin.android.ext.android.inject

class ListUsersFragment: BaseFragment() {

    // Binding //

    private lateinit var binding: FragmentListUsersBinding

    // ViewModel //

    private val viewModel: ListUsersViewModel by viewModel()

    // Mappers //

    private val mapperRenderables: ListUsersMapperRenderables by inject()

    // Vars //

    private val renderableItems: MutableList<RenderableDiff> = mutableListOf()

    // Default methods //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_list_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListUsersBinding.bind(view)
        initializeRecyclerView()
        setupActions()
        setupViewModel()
    }

    override fun onDestroy() {
        (binding.rv.adapter as? RendererDiffAdapter)?.dispose()
        viewModel.onDestroyDisposables()
        super.onDestroy()
    }

    // Render //

    private fun renderViewState(viewState: ListUsersViewState) {
        when (viewState) {
            ListUsersDefaultViewState -> {
                Log.d(TAG, "ListUsersDefaultViewState")
            }
            is ListUsersListReceivedViewState -> {
                Log.d(TAG, "ListUsersListReceivedViewState")
                putListIntoRecyclerView(viewState.state.list)
            }
            is ListUsersShowProgressViewState -> {
                Log.d(TAG, "ListUsersShowProgressViewState")
                renderProgress(viewState.state.isLoading)
            }
            is ListUsersErrorViewState -> {
                Log.d(TAG, "ListUsersErrorViewState")
            }
        }
    }

    // Setup ViewModel //

    private fun setupViewModel() {
        val listUsersViewState: Observer<ListUsersViewState> = Observer { state ->
            state?.let { renderViewState(state) }
        }
        viewModel.listUsersState.observe(viewLifecycleOwner, listUsersViewState)
        viewModel.setupBindings()
        viewModel.onCreateView.onNext(Unit)
    }

    // Recyclerview //

    private fun initializeRecyclerView() {
        binding.rv.apply {
            setHasFixedSize(false)
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = RendererDiffAdapter(
                renderableItems, RendererBuilder(ListUsersFactory())
            )
        }
    }

    private fun putListIntoRecyclerView(list: List<UsersPresentation>) {
        renderableItems.clear()
        renderableItems.addAll(mapperRenderables.toMapList(list))
        binding.rv.adapter?.notifyDataSetChanged()
    }

    // Setup Actions //

    private fun setupActions() {
        binding.rl.setOnRefreshListener {
            binding.rl.isRefreshing = false
            viewModel.onRefreshList.onNext(Unit)
        }
    }

    // Private methods //

    private fun renderProgress(show: Boolean) {
        if (show) {
            progressDelegate?.showProgress(getString(com.example.commonui.R.string.loading))
        } else {
            progressDelegate?.hideProgress()
        }
    }

}
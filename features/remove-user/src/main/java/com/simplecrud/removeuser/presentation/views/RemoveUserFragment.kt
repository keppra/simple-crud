package com.simplecrud.removeuser.presentation.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.simplecrud.base.presentation.BaseFragment
import com.simplecrud.removeuser.R
import com.simplecrud.removeuser.databinding.FragmentRemoveUserBinding
import com.simplecrud.removeuser.presentation.viewmodels.RemoveUserViewModel
import com.simplecrud.removeuser.presentation.viewstates.*
import com.simplecrud.utils.extensions.TAG
import org.koin.androidx.viewmodel.ext.android.viewModel

class RemoveUserFragment : BaseFragment() {

    // Binding //

    private lateinit var binding: FragmentRemoveUserBinding

    // ViewModel //

    private val viewModel: RemoveUserViewModel by viewModel()

    // Default methods //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_remove_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRemoveUserBinding.bind(view)
        setupViewModel()
        setupViews()
        setupActions()
    }

    override fun onDestroy() {
        viewModel.onDestroyDisposables()
        this.viewModelStore.clear()
        super.onDestroy()
    }

    // Render //

    private fun renderViewState(viewState: RemoveUserViewState) {
        when (viewState) {
            RemoveUserDefaultViewState -> {
                Log.d(TAG, "RemoveUserDefaultViewState")
            }
            is RemoveUserTextChangedViewState -> {
                Log.d(TAG, "RemoveUserTextChangedViewState")
            }
            is RemoveUserSuccessViewState -> {
                Log.d(TAG, "RemoveUserSuccessViewState")
                renderInformativeDialog(
                    title = context?.getString(com.example.commonui.R.string.success) ?: "",
                    details = context?.getString(com.example.commonui.R.string.remove_user_dialog_success_details) ?: "",
                    buttonText = context?.getString(com.example.commonui.R.string.alright) ?: "",
                )
            }
            is RemoveUserErrorViewState -> {
                Log.d(TAG, "RemoveUserErrorViewState")
                renderInformativeDialog(
                    title = context?.getString(com.example.commonui.R.string.error) ?: "",
                    details = context?.getString(com.example.commonui.R.string.error_details) ?: "",
                    buttonText = context?.getString(com.example.commonui.R.string.alright) ?: "",
                )
            }
        }
    }

    // Setup ViewModel //

    private fun setupViewModel() {
        val removeUserViewState: Observer<RemoveUserViewState> = Observer { state ->
            state?.let { renderViewState(state) }
        }
        viewModel.removeUserState.observe(viewLifecycleOwner, removeUserViewState)
        viewModel.setupBindings()

        viewModel.onCreateView.onNext(Unit)
    }

    // Setup Actions //

    private fun setupActions() {
        binding.etId.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrBlank()) {
                    viewModel.didOnChangeText.onNext(s.toString().toInt())
                }
            }
        })
        binding.button.setOnClickListener {
            viewModel.onTapButton.onNext(Unit)
        }
    }

    private fun setupViews() {
        binding.tilId.hint = context?.getString(com.example.commonui.R.string.id)
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
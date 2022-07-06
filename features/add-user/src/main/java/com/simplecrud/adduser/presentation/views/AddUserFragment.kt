package com.simplecrud.adduser.presentation.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.simplecrud.adduser.R
import com.simplecrud.adduser.databinding.FragmentAddUserBinding
import com.simplecrud.adduser.presentation.viewmodels.AddUserViewModel
import com.simplecrud.adduser.presentation.viewstates.*
import com.simplecrud.base.presentation.BaseFragment
import com.simplecrud.commonui.extensions.gone
import com.simplecrud.commonui.extensions.visible
import com.simplecrud.utils.extensions.TAG
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddUserFragment : BaseFragment() {

    // Binding //

    private lateinit var binding: FragmentAddUserBinding

    // ViewModel //

    private val viewModel: AddUserViewModel by viewModel()

    // Default methods //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddUserBinding.bind(view)
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

    private fun renderViewState(viewState: AddUserViewState) {
        when (viewState) {
            AddUserDefaultViewState -> {
                Log.d(TAG, "AddUserDefaultViewState")
            }
            is AddUserTextChangedViewState -> {
                Log.d(TAG, "AddUserTextChangedViewState")
            }
            is AddUserFormValidationResultViewState -> {
                Log.d(TAG, "AddUserFormValidationResultViewState")
                shouldRenderValidationElements(viewState.state.isNameValid, TypeField.USERNAME.ordinal)
                shouldRenderValidationElements(viewState.state.isBirthdateValid, TypeField.BIRTHDATE.ordinal)
            }
            is AddUserSuccessViewState -> {
                Log.d(TAG, "AddUserSuccessViewState")
                renderInformativeDialog(
                    title = context?.getString(com.example.commonui.R.string.success) ?: "",
                    details = context?.getString(com.example.commonui.R.string.add_user_dialog_success_details) ?: "",
                    buttonText = context?.getString(com.example.commonui.R.string.alright) ?: "",
                )
            }
            is AddUserErrorViewState -> {
                Log.d(TAG, "AddUserErrorViewState")
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
        val addUserViewState: Observer<AddUserViewState> = Observer { state ->
            state?.let { renderViewState(state) }
        }
        viewModel.addUserState.observe(viewLifecycleOwner, addUserViewState)
        viewModel.setupBindings()

        viewModel.onCreateView.onNext(Unit)
    }

    // Setup Actions //

    private fun setupActions() {
        binding.etName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.didOnChangeText.onNext(Pair(s.toString(), TypeField.USERNAME.ordinal))
            }
        })
        binding.etBirthdate.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.didOnChangeText.onNext(Pair(s.toString(), TypeField.BIRTHDATE.ordinal))
            }
        })
        binding.button.setOnClickListener {
            viewModel.onTapButton.onNext(Unit)
        }
    }

    private fun setupViews() {
        renderHints()
    }

    // Private methods //

    private fun renderHints() {
        binding.tilName.hint = context?.getString(com.example.commonui.R.string.full_name)
        binding.tilBirthdate.hint = context?.getString(com.example.commonui.R.string.birthdate)
        binding.tvValidationName.text = context?.getString(com.example.commonui.R.string.add_user_field_validation_name)
        binding.tvValidationBirthdate.text = context?.getString(com.example.commonui.R.string.add_user_field_validation_birthdate)
    }

    private fun shouldRenderValidationElements(isValid: Boolean, typeField: Int) {
        context?.let { context ->
            when (typeField) {
                TypeField.USERNAME.ordinal -> {
                    if (!isValid) {
                        binding.cvName.strokeWidth = 2
                        binding.cvName.strokeColor = ContextCompat.getColor(context, com.example.commonui.R.color.warning)
                        binding.tvValidationName.visible()
                    } else {
                        binding.cvName.strokeWidth = 0
                        binding.tvValidationName.gone()
                    }
                }
                TypeField.BIRTHDATE.ordinal -> {
                    if (!isValid) {
                        binding.cvBirthdate.strokeWidth = 2
                        binding.cvBirthdate.strokeColor = ContextCompat.getColor(context, com.example.commonui.R.color.warning)
                        binding.tvValidationBirthdate.visible()
                    } else {
                        binding.cvBirthdate.strokeWidth = 0
                        binding.tvValidationBirthdate.gone()
                    }
                }
            }
        }

    }

    private fun renderProgress(show: Boolean) {
        if (show) {
            progressDelegate?.showProgress(getString(com.example.commonui.R.string.loading))
        } else {
            progressDelegate?.hideProgress()
        }
    }

}
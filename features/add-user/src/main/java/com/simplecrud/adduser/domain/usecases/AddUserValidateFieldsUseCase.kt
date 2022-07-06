package com.simplecrud.adduser.domain.usecases

import com.simplecrud.base.domain.entities.Result
import com.simplecrud.base.domain.extensions.toResult
import com.simplecrud.commonui.extensions.fromDateToStringResource
import io.reactivex.rxjava3.core.Single

class AddUserValidateFieldsUseCase {
    fun invokeWith(name: String, birthdate: String): Single<ValidateFieldsReturnParams> =
        Single.just(
            ValidateFieldsReturnParams(
                isNameValid = isNameValid(name),
                isBirthdateValid = isBirthDateValid(birthdate),
                isFormValid = isFormValid(name = name, birthdate = birthdate)
            )
        )

    private fun isNameValid(name: String?): Boolean =
        !name.isNullOrEmpty() && name.length > 5

    private fun isBirthDateValid(birthdate: String?): Boolean =
        birthdate?.fromDateToStringResource()?.isCorrect ?: false

    private fun isFormValid(name: String?, birthdate: String?) =
        isNameValid(name) && isBirthDateValid(birthdate)

    data class ValidateFieldsReturnParams(
        val isNameValid: Boolean = false,
        val isBirthdateValid: Boolean = false,
        val isFormValid: Boolean = false,
    )

}
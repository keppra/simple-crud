package com.simplecrud.listusers.presentation.models

sealed class UsersPresentation

object DescriptionPresentation: UsersPresentation()

data class ItemPresentation(
    val name: String,
    val birthdate: String,
): UsersPresentation()
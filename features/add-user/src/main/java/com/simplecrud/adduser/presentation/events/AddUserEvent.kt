package com.simplecrud.adduser.presentation.events

sealed class AddUserEvent
object AddUserOnCreateViewEvent: AddUserEvent()
data class AddUserTextChangedEvent(val text: String, val type: Int): AddUserEvent()
object AddUserValidateDataEvent: AddUserEvent()
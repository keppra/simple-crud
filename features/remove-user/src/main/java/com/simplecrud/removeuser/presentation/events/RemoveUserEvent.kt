package com.simplecrud.removeuser.presentation.events

sealed class RemoveUserEvent
object RemoveUserOnCreateViewEvent: RemoveUserEvent()
data class RemoveUserTextChangedEvent(val id: Int): RemoveUserEvent()
object RemoveUserSendEvent: RemoveUserEvent()
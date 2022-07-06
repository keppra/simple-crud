package com.simplecrud.listusers.presentation.events

sealed class ListUsersEvent
object ListUsersOnCreateEvent: ListUsersEvent()
object ListUsersOnRefreshListEvent: ListUsersEvent()

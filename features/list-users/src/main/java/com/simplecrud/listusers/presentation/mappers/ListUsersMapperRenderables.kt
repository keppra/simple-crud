package com.simplecrud.listusers.presentation.mappers

import android.content.Context
import com.example.commonui.R
import com.simplecrud.commonui.extensions.fromLocalDateToStringResource
import com.simplecrud.commonui.extensions.DateFormatted
import com.simplecrud.listusers.presentation.models.DescriptionPresentation
import com.simplecrud.listusers.presentation.models.ItemPresentation
import com.simplecrud.listusers.presentation.models.UsersPresentation
import com.simplecrud.listusers.presentation.renderables.renders.DescriptionRow
import com.simplecrud.listusers.presentation.renderables.renders.ItemRow
import com.simplecrud.recyclerviewrenders.interfaces.RenderableDiff
import java.util.*

class ListUsersMapperRenderables(
    private val context: Context
) {
    fun toMapList(
        list: List<UsersPresentation>
    ): List<RenderableDiff> =
        list.map {
            when (it) {
                is DescriptionPresentation -> DescriptionRow(UUID.randomUUID().toString())
                is ItemPresentation -> toMapItemRow(it)
            }
        }

    private fun toMapItemRow(item: ItemPresentation): ItemRow =
        ItemRow(
            id = UUID.randomUUID().toString(),
            name = item.name,
            birthdate = printBirthDate(item.birthdate.take(10).fromLocalDateToStringResource())
        )

    private fun printBirthDate(dateFormatted: DateFormatted): String =
        if (dateFormatted.isCorrect) {
            dateFormatted.monthResource?.let { monthResource ->
                String.format(
                    context.getString(R.string.list_users_renderable_birthdate),
                    dateFormatted.day,
                    context.getString(monthResource),
                    dateFormatted.year
                )
            } ?: context.getString(R.string.list_users_renderable_not_specified)
        } else {
            context.getString(R.string.list_users_renderable_not_specified)
        }
}
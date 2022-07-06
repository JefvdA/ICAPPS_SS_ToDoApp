package com.icapps.summerschool.todoapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TodoItem(var description: String, var tasks: MutableList<String>): Parcelable {
}
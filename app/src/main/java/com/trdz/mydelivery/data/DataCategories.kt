package com.trdz.mydelivery.data

import com.trdz.mydelivery.utility.TYPE_HEAD

data class DataCategories(val id: String, var type: Int = TYPE_HEAD, val category: String = "", var state: Boolean = false)
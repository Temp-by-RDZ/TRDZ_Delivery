package com.trdz.mydelivery.model.source_server.dto_category


import com.google.gson.annotations.SerializedName

data class DTOCategories(
    @SerializedName("categories")
    val categories: List<DTOCategory>
)
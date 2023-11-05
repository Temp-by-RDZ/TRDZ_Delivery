package com.trdz.mydelivery.model.source_server.dto_category


import com.google.gson.annotations.SerializedName

data class DTOCategory(
    @SerializedName("idCategory")
    val idCategory: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String
)
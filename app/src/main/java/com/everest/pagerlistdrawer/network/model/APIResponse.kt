package com.everest.pagerlistdrawer.network.model

import com.google.gson.annotations.SerializedName

data class APIResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("data")
    val users: List<User>

)

data class User(

    @SerializedName("id")
    val id: Int,

    @SerializedName("email")
    val email: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("avatar")
    val avatar: String
)
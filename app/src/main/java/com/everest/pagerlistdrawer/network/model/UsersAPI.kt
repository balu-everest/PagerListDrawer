package com.everest.pagerlistdrawer.network.model

import retrofit2.http.GET

interface UsersAPI {

    @GET("api/users")
    suspend fun getUsers(): APIResponse

}
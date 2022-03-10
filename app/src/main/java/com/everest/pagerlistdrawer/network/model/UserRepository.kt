package com.everest.pagerlistdrawer.network.model

import com.everest.pagerlistdrawer.ui.User

class UserRepository(
    val usersAPI: UsersAPI
) {
    suspend fun getUsers(): List<User> {
        val userReponse = usersAPI.getUsers()
        return convertDTOIntoUIModel(userReponse.users)
    }

    private fun convertDTOIntoUIModel(users: List<com.everest.pagerlistdrawer.network.model.User>): List<User> {
        return users.map {
            User(
                it.id, it.email, it.firstName, it.lastName, it.email
            )
        }
    }
}
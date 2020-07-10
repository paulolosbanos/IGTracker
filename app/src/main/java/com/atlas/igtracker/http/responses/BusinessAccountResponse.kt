package com.atlas.igtracker.http.responses

data class BusinessAccountResponse(
    val id: String,
    val instagram_business_account: InstagramBusinessAccount
)

data class InstagramBusinessAccount(
    val id: String
)
package com.atlas.igtracker.http.responses

import java.io.Serializable

data class AccountsResponseWrapper(
    val data: List<AccountsResponseData>
): Serializable

data class AccountsResponseData(
    val access_token: String,
    val id: String
)

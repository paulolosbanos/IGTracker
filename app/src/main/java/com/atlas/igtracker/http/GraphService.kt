package com.atlas.igtracker.http

import com.atlas.igtracker.http.responses.AccountsResponseWrapper
import com.atlas.igtracker.http.responses.BusinessAccountResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GraphService {
    @GET("me/accounts")
    fun accounts(@Query("access_token") accessToken: String): Observable<AccountsResponseWrapper>

    @GET("{pageId}")
    fun businessAccounts(@Path("pageId") pageId: String, @Query("fields") fields: String, @Query("access_token") accessToken: String): Observable<BusinessAccountResponse>

    //@GET("{ig-user-id/media}")

    //@GET("{ig-media-id}")
}
package hu.ait.spotifydemo

import hu.ait.spotifydemo.data.Token
import retrofit2.Call
import retrofit2.http.*


//client id = ff8b337922a5480ebc7657866820d724
//client secret = 7dcb859c974342f39fdb3c373e124462
//encoded = Basic ZmY4YjMzNzkyMmE1NDgwZWJjNzY1Nzg2NjgyMGQ3MjQ6N2RjYjg1OWM5NzQzNDJmMzlmZGIzYzM3M2UxMjQ0NjI=

interface Authorization {
    @Headers("Authorization: Basic ZmY4YjMzNzkyMmE1NDgwZWJjNzY1Nzg2NjgyMGQ3MjQ6N2RjYjg1OWM5NzQzNDJmMzlmZGIzYzM3M2UxMjQ0NjI=",
                    "Content-Type: application/x-www-form-urlencoded")
    @POST("/api/token")
    fun getToken(
                 @Query("grant_type") grantType: String = "client_credentials"): Call<Token>
}




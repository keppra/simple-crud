package com.simplecrud.api.services
import com.simplecrud.api.models.UserModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import java.time.format.DateTimeFormatter

interface ApiServices {
    @GET("api/User")
    fun getUsers(): Single<List<UserModel>>

    @FormUrlEncoded
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("api/User")
    fun addUser(
        @Field("name") name: String?,
        @Field("birthdate") birthdate: String?
    ): Completable
}
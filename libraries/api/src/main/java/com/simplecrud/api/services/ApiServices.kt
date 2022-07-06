package com.simplecrud.api.services
import com.simplecrud.api.models.UserModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import java.time.LocalDate

interface ApiServices {
    @GET("api/User")
    fun getUsers(): Single<List<UserModel>>

    @FormUrlEncoded
    @POST("api/User")
    fun addUser(
        @Field("name") name: String?,
        @Field("birthdate") birthdate: String?
    ): Single<UserModel>
}
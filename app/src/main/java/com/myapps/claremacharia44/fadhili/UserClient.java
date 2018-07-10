package com.myapps.claremacharia44.fadhili;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserClient  {
    @FormUrlEncoded
    @POST("/donor")
    Call<User> register(
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("email") String email,
            @Field("password") String password
    );
}

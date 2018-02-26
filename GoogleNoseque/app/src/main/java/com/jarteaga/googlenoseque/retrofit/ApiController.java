package com.jarteaga.googlenoseque.retrofit;

import com.jarteaga.googlenoseque.models.Notas;
import com.jarteaga.googlenoseque.retrofit.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jaduran on 21/02/2018.
 */

public interface ApiController {

    @FormUrlEncoded
    @POST("/keeper/api/auth/register")
    Call<User> register(@Field("nombre") String nombre, @Field("email") String email, @Field("password")String password);
    @FormUrlEncoded
    @POST("/keeper/api/auth/login")
    Call<User> logging(@Field("email") String email, @Field("password")String password);

    @GET("/keeper/api/nota/lista")
    Call<List<Notas>> listNotas(@Query("X-API-KEY") String key);

    @FormUrlEncoded
    @POST("/keeper/api/nota/nueva")
    Call<Notas> addNota(@Query("X-API-KEY")String key, @Field("titulo") String titulo, @Field("descripcion") String descripcion, @Field("idcategoria")String categoria);
}

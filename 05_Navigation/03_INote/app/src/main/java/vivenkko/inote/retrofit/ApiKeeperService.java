package vivenkko.inote.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vivenkko.inote.model.Note;
import vivenkko.inote.model.User;

/**
 * Created by magomez on 22/02/2018.
 */

public interface ApiKeeperService {
    @FormUrlEncoded
    @POST("/keeper/api/auth/register")
    Call<User> register(@Field("nombre") String name, @Field("email") String email, @Field("password")String password);

    @FormUrlEncoded
    @POST("/keeper/api/auth/login")
    Call<User> login(@Field("email") String email, @Field("password")String password);

    @GET("/keeper/api/nota/lista")
    Call<List<Note>> listNotes(@Query("X-API-KEY") String key);

    @FormUrlEncoded
    @POST("/keeper/api/nota/nueva")
    Call<Note> addNote(@Header("X-API-KEY") String key, @Field("titulo") String title,
                       @Field("descripcion") String description, @Field("idcategoria")String category);
}

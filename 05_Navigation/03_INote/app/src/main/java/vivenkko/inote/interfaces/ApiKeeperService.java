package vivenkko.inote.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vivenkko.inote.model.Note;

/**
 * Created by magomez on 22/02/2018.
 */

public interface ApiKeeperService {

    @POST("/auth/register")
    Call<Note> register(@Query("X-API-KEY") String apikey);

    @POST("/auth/login")
    Call<Note> login(@Query("X-API-KEY") String apikey);

    @POST("/auth/list")
    Call<Note> userList(@Query("X-API-KEY") String apikey);

    @POST("/nota/nueva")
    Call<Note> newNote(@Query("X-API-KEY") String apikey);

    @GET("taller/lista")
    Call<Note> noteList(@Query("X-API-KEY") String apiKey);

}

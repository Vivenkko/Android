package vivenkko.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.weather.model.Retrofit.ApiController;
import vivenkko.weather.model.Retrofit.ServiceGeneratorLog;
import vivenkko.weather.model.Retrofit.ServiceGeneratorWeather;
import vivenkko.weather.model.Retrofit.User;

public class RegisterActivity extends AppCompatActivity {
    EditText user, email, password;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn = findViewById(R.id.buttonRegister);
        user = findViewById(R.id.editTextUserRegister);
        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPassRegister);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamamos a la API a través del servicio
                ApiController api = ServiceGeneratorLog.createService(ApiController.class);

                // Recogemos los datos introducidos por el usuario y los guardamos en un usuario nuevo
                User usuario = new User(user.getText().toString(), email.getText().toString(), password.getText().toString());

                // Hacemos la llamada[call] a la API
                Call<User> call = api.register(usuario.getNombre(),usuario.getEmail(),usuario.getPassword());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            // QUE HACE ESTO?
                            User responseUser = response.body();
                            String key = responseUser.getKey();

                            Toast.makeText(RegisterActivity.this, "Register complete", Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegisterActivity.this, key, Toast.LENGTH_SHORT).show();

                            // Lanza el MainActivity (no necesario cerrar el register?)
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Register incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "There was a problem with the register", Toast.LENGTH_SHORT).show();
                        Log.e("Retrofit Fail", t.toString());
                    }
                });
            }
        });


    }
}

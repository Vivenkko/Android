package vivenkko.inote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.inote.model.User;
import vivenkko.inote.retrofit.ApiKeeperService;
import vivenkko.inote.retrofit.ServiceGenerator;

public class LoginActivity extends AppCompatActivity {
    Button btn;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Paso 0: Rescatar los elementos de la UI
        btn = findViewById(R.id.buttonLogin);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);

    }

    public void onLoginButtonClick() {
        ApiKeeperService api = ServiceGenerator.createService(ApiKeeperService.class);

        Call<User> call = api.login(email.getText().toString(), password.getText().toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User respuesta = response.body();
                    Toast.makeText(LoginActivity.this, "Login complete", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    //Intercomunicador [guarda una variable en memoria] entre activities o fragments
                    bundle.putString("key", respuesta.getKey());
                    //Necesario entre activities, no entre fragments. Intent comunica activities
                    intent.putExtras(bundle);
                    //Carga la actividad nueva
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Incorrect user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Ups. Something goes wrong, sorry.", Toast.LENGTH_SHORT).show();
            }
        });
    }



}

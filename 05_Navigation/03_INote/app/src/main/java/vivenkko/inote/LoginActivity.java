package vivenkko.inote;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
    Button buttonLogin, buttonRegister;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Paso 0: Rescatar los elementos de la interfaz de usuario
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);

    }

    public void onLoginButtonClick(View view) {
        //Llamo a la API a través del servicio
        ApiKeeperService api = ServiceGenerator.createService(ApiKeeperService.class);

        //Creo la llamada del Login
        Call<User> call = api.login(email.getText().toString(), password.getText().toString());

        //Lanzo el Call
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User respuesta = response.body();
                    //Toast.makeText(LoginActivity.this, "Login complete", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    //Intercomunicador [guarda una variable en memoria] entre activities o fragments
                    bundle.putString("key", respuesta.getKey());
                    //Necesario entre activities, no entre fragments. Intent comunica activities
                    intent.putExtras(bundle);
                    //Carga la actividad nueva
                    startActivity(intent);
                    finish();
                }else {
                    //Toast.makeText(LoginActivity.this, "Incorrect user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.makeText(LoginActivity.this, "Ups. Something goes wrong, sorry.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onForgotPasswordClick(View view) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_recover_email)
                .setTitle(R.string.dialog_recover_pass);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.recover_password, null);
        builder.setView(dialogView);


        // Añadir botones
        builder.setPositiveButton(R.string.dialog_recover_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButton(R.string.dialog_button_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Cerramos el cuadro de diálogo
                dialog.dismiss();
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        // 4. Mostrar el diálogo en la pantalla
        dialog.show();
    }


    public void onRegisterButtonClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}

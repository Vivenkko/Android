package vivenkko.weather;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.weather.model.retrofit.ApiOpenWeather;
import vivenkko.weather.model.retrofit.User;
import vivenkko.weather.model.retrofit.services.ServiceGeneratorLog;

public class LoginActivity extends AppCompatActivity {
    Button buttonLogin, buttonReg;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonReg = findViewById(R.id.buttonReg);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
    }

    public void onLoginButtonClick(View view) {
        ApiOpenWeather api = ServiceGeneratorLog.createService(ApiOpenWeather.class);

        Call<User> call = api.login(email.getText().toString(), password.getText().toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User respuesta = response.body();
                    Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    bundle.putString("key", respuesta.getKey());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Wrong user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "There was a problem with the login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onForgotClick(View view) {
        // 1. Instancia un AlertDialog.Builder con su constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Encadena juntos varios métodos setter para definir las características del dialog
        builder.setMessage(R.string.dialog_recover_email)
                .setTitle(R.string.dialog_recover_pass);

        // 3. Infla el layout (del dialog)
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.recover_password, null);
        builder.setView(dialogView);

        // Añadir botones
        builder.setPositiveButton(R.string.dialog_recover_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButton(R.string.dialog_recover_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Cerramos el cuadro de diálogo
                dialog.dismiss();
            }
        });

        // 3. Obtiene el AlertDialog desde create()
        AlertDialog dialog = builder.create();

        // 4. Mostrar el diálogo en la pantalla
        dialog.show();
    }

    public void onRegButtonClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}

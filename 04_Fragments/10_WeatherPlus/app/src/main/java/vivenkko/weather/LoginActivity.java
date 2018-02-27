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
import vivenkko.weather.model.Retrofit.ApiController;
import vivenkko.weather.model.Retrofit.ServiceGeneratorLog;
import vivenkko.weather.model.Retrofit.User;

public class LoginActivity extends AppCompatActivity {
    Button btn;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.buttonLogin);
        email = findViewById(R.id.editTextUserRegister);
        password = findViewById(R.id.editTextPassword);
    }

    public void onLoginButtonClick(View view) {
        ApiController api = ServiceGeneratorLog.createService(ApiController.class);

        Call<User> call = api.login(email.getText().toString(), password.getText().toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User respuesta = response.body();
                    Toast.makeText(LoginActivity.this, "Registro perfecto", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    bundle.putString("key", respuesta.getKey());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void onRecoverButtonClick(View view) {
        // 1. Instancia un AlertDialog.Builder con su constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Encadena juntos varios metodos setter para definir las características del dialog
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

    public void registrar(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}


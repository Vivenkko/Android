package com.jarteaga.googlenoseque;

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

import com.jarteaga.googlenoseque.fragments.NotasListFragment;
import com.jarteaga.googlenoseque.retrofit.ApiController;
import com.jarteaga.googlenoseque.retrofit.ServiceGenerator;
import com.jarteaga.googlenoseque.retrofit.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.buttonLogging);
        email = findViewById(R.id.editTextNombreRegistro);
        password = findViewById(R.id.editTextPassword);
    }

    public void loggin(View view) {
        ApiController api = ServiceGenerator.createService(ApiController.class);

        Call<User> call = api.logging(email.getText().toString(), password.getText().toString());

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

    public void recuperarPass(View view) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_compartir_mensaje)
                .setTitle(R.string.dialog_compartir_titulo);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.recordar_password, null);
        builder.setView(dialogView);


        // Añadir botones
        builder.setPositiveButton(R.string.dialog_compartir_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButton(R.string.dialog_compartir_cancel, new DialogInterface.OnClickListener() {
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


    public void registrar(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}


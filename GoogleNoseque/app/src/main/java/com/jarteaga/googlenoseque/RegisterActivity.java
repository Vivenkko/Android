package com.jarteaga.googlenoseque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jarteaga.googlenoseque.retrofit.ApiController;
import com.jarteaga.googlenoseque.retrofit.ServiceGenerator;
import com.jarteaga.googlenoseque.retrofit.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText nombre, email, password;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn = findViewById(R.id.buttonLogging);
        nombre = findViewById(R.id.editTextNombreRegistro);
        email = findViewById(R.id.editTextPassword);
        password = findViewById(R.id.editTextPassRegistro);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiController api = ServiceGenerator.createService(ApiController.class);


                User usuario = new User(nombre.getText().toString(), email.getText().toString(), password.getText().toString());


                Call<User> call = api.register(usuario.getNombre(),usuario.getEmail(),usuario.getPassword());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User res = response.body();
                            String key = res.getKey();
                            Toast.makeText(RegisterActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegisterActivity.this, key, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Registro incorrecto", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });


    }
}

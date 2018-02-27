package vivenkko.inote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class RegisterActivity extends AppCompatActivity {
    EditText user, email, password;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn = findViewById(R.id.buttonLogin);
        user = findViewById(R.id.editTextUserRegister);
        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPassRegister);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiKeeperService api = ServiceGenerator.createService(ApiKeeperService.class);

                User userRegister = new User(user.getText().toString(), email.getText().toString(), password.getText().toString());

                Call<User> call = api.register(userRegister.getName(),userRegister.getEmail(),userRegister.getPassword());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User res = response.body();
                            String key = res.getKey();
                            Toast.makeText(RegisterActivity.this, "Register complete", Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegisterActivity.this, key, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Register failure", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Retrofit encounters a problem", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

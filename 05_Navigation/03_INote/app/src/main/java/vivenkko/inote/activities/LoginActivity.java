package vivenkko.inote.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import vivenkko.inote.R;

public class LoginActivity extends AppCompatActivity {

    private static EditText email;
    private static EditText password;
    private static Button loginButton;
    private static ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();
    }

    public void LoginButton() {
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        logo = findViewById(R.id.imageViewLogo);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(LoginActivity.this, "Loggin", Toast.LENGTH_SHORT).show();
                    Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                    intentLogin.putExtra("key","");
                    startActivity(intentLogin);
                } else {
                    Toast.makeText(LoginActivity.this, "Email or password wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}

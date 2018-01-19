package com.miguelcr.a01_tresenraya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class NewGameActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextP1, editTextP2;
    Button buttonNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        editTextP1 = findViewById(R.id.editTextPlayer1Name);
        editTextP2 = findViewById(R.id.editTextPlayer2Name);
        buttonNewGame = findViewById(R.id.buttonGame);

        buttonNewGame.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // Rescatamos los nombres de los jugadores
        String p1Name = editTextP1.getText().toString();
        String p2Name = editTextP2.getText().toString();

        // Invocamos el lanzamiento de la pantalla de Juego
        Intent i = new Intent(NewGameActivity.this, BoardActivity.class);

        // Pasamos por parámetro el nombre de los jugadores
        i.putExtra(Constantes.EXTRA_PLAYER_1, p1Name);
        i.putExtra(Constantes.EXTRA_PLAYER_2, p2Name);

        // Ejecutar el lanzamiento del BoardActivity
        startActivity(i);

    }
}

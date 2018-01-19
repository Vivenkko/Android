package com.miguelcr.a01_tresenraya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewP1, textViewP2;
    ImageView iv0, iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        textViewP1 = findViewById(R.id.textViewP1);
        textViewP2 = findViewById(R.id.textViewP2);
        iv0 = findViewById(R.id.imageViewC1);
        iv1 = findViewById(R.id.imageViewC2);
        iv2 = findViewById(R.id.imageViewC3);
        iv3 = findViewById(R.id.imageViewC4);
        iv4 = findViewById(R.id.imageViewC5);
        iv5 = findViewById(R.id.imageViewC6);
        iv6 = findViewById(R.id.imageViewC7);
        iv7 = findViewById(R.id.imageViewC8);
        iv8 = findViewById(R.id.imageViewC9);

        // Rescatar los nombres de los jugadores
        Bundle extras = getIntent().getExtras();

        String p1Name = extras.getString(Constantes.EXTRA_PLAYER_1);
        String p2Name = extras.getString(Constantes.EXTRA_PLAYER_2);
    }

    private void eventListeners() {
        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        iv7.setOnClickListener(this);
        iv8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int idCasilla = v.getId();
        ImageView casillaSeleccionada = null;
        boolean isPlaying1 = true;

        switch (idCasilla) {
            case R.id.imageViewC1:
                casillaSeleccionada = iv0;
                break;
            case R.id.imageViewC2:
                casillaSeleccionada = iv1;
                break;
            case R.id.imageViewC3:
                casillaSeleccionada = iv2;
                break;
            case R.id.imageViewC4:
                casillaSeleccionada = iv3;
                break;
            case R.id.imageViewC5:
                casillaSeleccionada = iv4;
                break;
            case R.id.imageViewC6:
                casillaSeleccionada = iv5;
                break;
            case R.id.imageViewC7:
                casillaSeleccionada = iv6;
                break;
            case R.id.imageViewC8:
                casillaSeleccionada = iv7;
                break;
            case R.id.imageViewC9:
                casillaSeleccionada = iv8;
                break;
        }

        if(isPlaying1) {
            casillaSeleccionada.setImageResource(R.drawable.ic_p1);
        } else {
            casillaSeleccionada.setImageResource(R.drawable.ic_p2);
        }

        isPlaying1 = !isPlaying1;

    }
}

package com.jarteaga.googlenoseque;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jarteaga.googlenoseque.fragments.EditNotaFragment;
import com.jarteaga.googlenoseque.fragments.NotasListFragment;
import com.jarteaga.googlenoseque.models.Notas;
import com.jarteaga.googlenoseque.retrofit.ApiController;
import com.jarteaga.googlenoseque.retrofit.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String key;
    EditText titulo, descripcion, categoria;
    Fragment fragment;
    FragmentTransaction ft;
    ImageView editImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editImage = findViewById(R.id.imageViewEditNota);

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new EditNotaFragment();
                newFragment.show(getSupportFragmentManager(), "NuevoUsuarioDialogFragment");

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("key");
        Bundle args = new Bundle();
        args.putString("apikey", key);

        fragment = new NotasListFragment();
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,fragment)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //aqui tenemos que crear el dialogo
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setTitle(R.string.dialog_add_nota);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.add_nota, null);
                builder.setView(dialogView);


                titulo = dialogView.findViewById(R.id.tituloNota);
                descripcion = dialogView.findViewById(R.id.descripcionNota);
                categoria = dialogView.findViewById(R.id.categoriaNotas);


                // Añadir botones
                builder.setPositiveButton(R.string.dialog_compartir_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        crearNotas();
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
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            new NotasListFragment();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void crearNotas(){
        ApiController api = ServiceGenerator.createService(ApiController.class);


        Call<Notas> call = api.addNota(key ,titulo.getText().toString(), descripcion.getText().toString(), categoria.getText().toString());

        call.enqueue(new Callback<Notas>() {
            @Override
            public void onResponse(Call<Notas> call, Response<Notas> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Nota creada", Toast.LENGTH_SHORT).show();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(fragment);
                    ft.attach(fragment);
                    ft.commit();

                }
            }

            @Override
            public void onFailure(Call<Notas> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void editNote(View view) {

    }
}

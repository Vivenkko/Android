package vivenkko.inote;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.inote.model.Note;
import vivenkko.inote.retrofit.ApiKeeperService;
import vivenkko.inote.retrofit.ServiceGenerator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String key;
    EditText title, description, category;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    ImageView editImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        editImage = findViewById(R.id.imageViewEdit);
//
//        editImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogFragment newFragment = new EditNoteFragment();
//                newFragment.show(getSupportFragmentManager(), "NewUserDialogFragment");
//            }
//        });

        // Automático
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("key");
        Bundle args = new Bundle();
        args.putString("apikey", key);

        fragment = new NotesListFragment();
        fragment.setArguments(args);

        //Cargamos por defecto el fragment de notas
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_content_main, fragment)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingButtonNewNote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //aqui tenemos que crear el dialogo
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setTitle(R.string.dialog_add_nota);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.add_note, null);
                builder.setView(dialogView);


                title = dialogView.findViewById(R.id.editTextTitleNote);
                description = dialogView.findViewById(R.id.editTextDescriptionNote);
                category = dialogView.findViewById(R.id.editTextCategoryNote);


                // Añadir botones
                builder.setPositiveButton(R.string.dialog_add_nota, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        addNote();
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
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addNote(){
        ApiKeeperService api = ServiceGenerator.createService(ApiKeeperService.class);

        Call<Note> call = api.addNote(key ,title.getText().toString(), description.getText().toString(), category.getText().toString());
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Nota creada", Toast.LENGTH_SHORT).show();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.detach(fragment);
                    fragmentTransaction.attach(fragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

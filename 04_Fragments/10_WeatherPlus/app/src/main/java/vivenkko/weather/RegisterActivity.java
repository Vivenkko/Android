package vivenkko.weather;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.weather.model.retrofit.ApiOpenWeather;
import vivenkko.weather.model.retrofit.FilesUtils;
import vivenkko.weather.model.retrofit.services.ServiceGeneratorLog;
import vivenkko.weather.model.retrofit.User;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RegisterActivity extends AppCompatActivity {
    EditText user, email, password;
    Button buttonRegister;
    ImageView image;
    Uri selectedImage = null;
    Context ctx;
    public static final int PICK_IMAGE = 1;
    String mail, passw, display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegister = findViewById(R.id.buttonRegister);
        user = findViewById(R.id.editTextUserRegister);
        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPassRegister);
        image = findViewById(R.id.imageViewRegister);
        ctx = getBaseContext();

        mail = email.getText().toString();
        passw = password.getText().toString();
        display = user.getText().toString();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedImage != null) {
                    ApiOpenWeather api = ServiceGeneratorLog.createService(ApiOpenWeather.class);

                    //Rescatamos el fichero
                    String strFile = FilesUtils.getFilePath(ctx, selectedImage);
                    File file = new File(strFile);

                    //Creamos el elemento part asociado a este
                    RequestBody requestFile =
                            RequestBody.create(
                                    MediaType.parse(getContentResolver().getType(selectedImage)),
                                    file
                            );

                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("photo", file.getName(), requestFile);

//                    //Creamos las partes de tipo texto
//                    RequestBody email = RequestBody.create(MultipartBody.FORM,);
//                    RequestBody password = RequestBody.create(MultipartBody.FORM, "40.00000, 0.00000");
//                    RequestBody displayName = RequestBody.create(MultipartBody.FORM, "40.00000, 0.00000");
//                    RequestBody displayName = RequestBody.create(MultipartBody.FORM, "40.00000, 0.00000");
//
//
//
//                    Call<ResponseBody> call = api.uploadFile(body, title, coords);
//
//                    call.enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            if (response.isSuccessful()) {
//                                Log.d("Upload", "Éxito");
//                                Log.d("Upload", response.body().toString());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            Log.e("Upload", t.getMessage());
//                        }
//                    });

                }




            }
        });}

//        buttonRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Llamamos a la API a través del servicio
//                ApiOpenWeather api = ServiceGeneratorLog.createService(ApiOpenWeather.class);
//
//                // Recogemos los datos introducidos por el usuario y los guardamos en un usuario nuevo
//                User usuario = new User(user.getText().toString(), email.getText().toString(), password.getText().toString(), i);
//
//                // Hacemos la llamada[call] a la API
//                Call<User> call = api.register(usuario.getDisplayName(),usuario.getEmail(),usuario.getAvatar());
//                call.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        if (response.isSuccessful()) {
//                            // Se rescata el usuario anteriormente creado para poder obtener su key
//                            User responseUser = response.body();
//                            String key = responseUser.getKey();
//
//                            Toast.makeText(RegisterActivity.this, "Register complete", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(RegisterActivity.this, key, Toast.LENGTH_SHORT).show();
//
//                            // Lanza el MainActivity
//                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//                            finish();
//                        }else {
//                            Toast.makeText(RegisterActivity.this, "Register incorrect", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//                        Toast.makeText(RegisterActivity.this, "There was a problem with the register", Toast.LENGTH_SHORT).show();
//                        Log.e("retrofit Fail", t.toString());
//                    }
//                });
//            }
//        });
//    }

    public void pickFromGal(){
        Intent getIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        getIntent.setType("image/*");
        startActivityForResult(getIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==PICK_IMAGE){
                onSelectFromGalleryResult(data);
            }
        }
    }

    private void onSelectFromGalleryResult(Intent data) {

        if (data != null) {
            try {
                Uri chosenImageUri = data.getData();
                selectedImage = chosenImageUri;
                Picasso
                        .with(RegisterActivity.this)
                        .load(chosenImageUri)
                        .into(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean mayRequestStoragePermission(View view) {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED))
            return true;

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(view, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {

                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, 100);
                }
            });
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, 100);
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
                //mOptionButton.setEnabled(true);
            }
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permisos denegados");
            builder.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });

            builder.show();
        }
    }

}

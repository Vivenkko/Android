package vivenkko.menuopciones;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textInputEditAsunto;
    private TextInputEditText textInputEditEmails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_share:
                showDialogCompartir();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogCompartir() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_share_message)
                .setTitle(R.string.dialog_share_title);

        // 3. Añadir layout personalizado
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_share, null);
        builder.setView(dialogView);
        textInputEditEmails = dialogView.findViewById(R.id.textInputEditEmails);
        textInputEditAsunto = dialogView.findViewById(R.id.textInputEditAsunto);

        // 4. Add the buttons
        builder.setPositiveButton(R.string.dialog_share_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button. Envia email
                String[] emails = {textInputEditEmails.getText().toString()};
                composeEmail(emails, textInputEditAsunto.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.dialog_share_no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });

        // 5. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        // 6. Mostrar el dialogo por pantalla
        dialog.show();
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}

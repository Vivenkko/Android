package vivenkko.inote;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EditNoteFragment extends DialogFragment {
    EditText title, description, category;

    private String mParam1;
    private String mParam2;

    public EditNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 1. Aquí creo el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(R.string.dialog_add_nota);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_note, null);
        builder.setView(dialogView);


        title = dialogView.findViewById(R.id.editTextTitleNote);
        description = dialogView.findViewById(R.id.editTextDescriptionNote);
        category = dialogView.findViewById(R.id.editTextCategoryNote);


        // Añadir botones
        builder.setPositiveButton(R.string.dialog_edit_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

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
        return builder.create();
    }
}
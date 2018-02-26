package com.jarteaga.googlenoseque.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jarteaga.googlenoseque.MainActivity;
import com.jarteaga.googlenoseque.R;


public class EditNotaFragment extends DialogFragment {
    EditText titulo, descripcion, categoria;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EditNotaFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //aqui tenemos que crear el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
        return builder.create();
    }


}

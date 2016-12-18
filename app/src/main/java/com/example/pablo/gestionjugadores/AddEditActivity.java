package com.example.pablo.gestionjugadores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEditActivity extends AppCompatActivity {

    private EditText edtNombre, edtEquipo, edtEdad, edtDescripcion;
    private Spinner listaPosiciones;
    private Button btnAddEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtEquipo = (EditText)findViewById(R.id.edtEquipo);
        edtEdad = (EditText)findViewById(R.id.edtEdad);
        edtDescripcion = (EditText)findViewById(R.id.edtDescripcion);
        btnAddEdit = (Button)findViewById(R.id.btnAddEdit);
        listaPosiciones = (Spinner) findViewById(R.id.listaPosiciones);

        String[] posiciones = {"Portero", "Defensa", "Mediocentro", "Delantero"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, posiciones);
        listaPosiciones.setAdapter(adapter);

        final Jugador j = (Jugador) getIntent().getSerializableExtra("jugador");
        if(j != null){
            edtNombre.setText(j.getNombre());
            edtEquipo.setText(j.getEquipo());
            edtEdad.setText(String.valueOf(j.getEdad()));
            edtDescripcion.setText(j.getDescripcion());
            listaPosiciones.setSelection(j.getPosicion());
            btnAddEdit.setText(getResources().getString(R.string.editar));
        }


        btnAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtNombre.getText().toString().matches("") && !edtEquipo.getText().toString().matches("") && !edtEdad.getText().toString().matches("") && !edtDescripcion.getText().toString().matches("")) {
                    Jugador jug = new Jugador(null, edtNombre.getText().toString(),
                            edtEquipo.getText().toString(),
                            Integer.parseInt(edtEdad.getText().toString()),
                            listaPosiciones.getSelectedItemPosition(),
                            edtDescripcion.getText().toString());

                    if (j != null) {
                        jug.setId(j.getId());
                    }

                    Intent i = new Intent();
                    i.putExtra("jugador", jug);
                    setResult(RESULT_OK, i);
                    finish();
                } else{
                    Toast.makeText(AddEditActivity.this, R.string.mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

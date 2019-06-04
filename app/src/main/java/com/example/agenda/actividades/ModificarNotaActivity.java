package com.example.agenda.actividades;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.datos.ListaDatos;
import com.example.agenda.datos.Nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.agenda.datos.ListaDatos.listaNotas;

public class ModificarNotaActivity extends AppCompatActivity {

    EditText editFecha;
    Spinner spiCategoria;
    EditText editTextoNuevaNota;
    Button btnCrear;
    Button btnCancelar;
    TextView txtNuevaNota;
    Calendar myCalendar;

    String myFormat = "dd MMMM yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
    Calendar fechaAux;

    Nota notaAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);

        editFecha = findViewById((R.id.editFecha));
        spiCategoria = findViewById(R.id.spiCategoria);
        editTextoNuevaNota = findViewById(R.id.editTextoNuevaNota);
        btnCrear = findViewById(R.id.btnCrear);
        btnCancelar = findViewById(R.id.btnCancelar);
        txtNuevaNota = findViewById(R.id.txtNuevaNota);
        Bundle extras = getIntent().getExtras();
        int indiceNota = extras.getInt("nota", 0);

        notaAux = listaNotas.get(indiceNota);

        myCalendar = notaAux.getFecha();
        txtNuevaNota.setText("Modificar nota");
        fechaAux = notaAux.getFecha();
        editFecha.setText(sdf.format(fechaAux.getTime()));

        //rellenar el spinner
        ArrayAdapter<String> dataAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Nota.getCategorias());
        spiCategoria.setAdapter(dataAdapter);

        spiCategoria.setSelection(notaAux.getIndexCategoria());
        editTextoNuevaNota.setText(notaAux.getTexto());
        btnCrear.setText("Modificar");

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerFecha(Calendar.getInstance());
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = spiCategoria.getSelectedItem().toString();
                String txtTexto = editTextoNuevaNota.getText().toString();
                notaAux.setFecha(myCalendar);
                notaAux.setTexto(txtTexto);
                notaAux.setCategoria(categoria);
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void leerFecha(Calendar fecha)
    {
        int year = fecha.get(Calendar.YEAR);
        int month = fecha.get(Calendar.MONTH);
        int day = fecha.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog (ModificarNotaActivity.this,
                datePickerListener,
                year,
                month,
                day);
        datePickerDialog.setTitle("Selecciona la fecha");
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            editFecha.setText(sdf.format(myCalendar.getTime()));
        }
    };
}

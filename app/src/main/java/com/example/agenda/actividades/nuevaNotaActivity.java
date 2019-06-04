package com.example.agenda.actividades;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class nuevaNotaActivity extends AppCompatActivity {

    EditText editFecha;
    Spinner spiCategoria;
    EditText editTextoNuevaNota;
    Button btnCrear;
    Button btnCancelar;


    String myFormat = "dd MMMM yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);

        editFecha = findViewById((R.id.editFecha));
        spiCategoria = findViewById(R.id.spiCategoria);
        editTextoNuevaNota = findViewById(R.id.editTextoNuevaNota);
        btnCrear = findViewById(R.id.btnCrear);
        btnCancelar = findViewById(R.id.btnCancelar);

        editFecha.setText(sdf.format(myCalendar.getTime()));

        String[] categorias = {"Urgente", "Importante", "Normal"};
        spiCategoria.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Nota.getCategorias()));

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerFecha(Calendar.getInstance());
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = spiCategoria.getSelectedItem().toString();
                String txtTexto = editTextoNuevaNota.getText().toString();
                ListaDatos.listaNotas.add(new Nota(myCalendar, txtTexto, categoria));
                finish();
            }
        });
    }

    private void leerFecha(Calendar fecha)
    {
        int year = fecha.get(Calendar.YEAR);
        int month = fecha.get(Calendar.MONTH);
        int day = fecha.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog (nuevaNotaActivity.this,
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
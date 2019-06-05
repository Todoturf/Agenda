package com.example.agenda.actividades;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.datos.Nota;

import java.util.ArrayList;

import static com.example.agenda.R.*;
import static com.example.agenda.datos.ListaDatos.listaNotas;


public class MainActivity extends AppCompatActivity {

    ListView lstNotas;
    ListadoNotasAdapter listadoNotasAdapter;

    FloatingActionButton fbNuevaNota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        lstNotas = findViewById(id.lstNotas);
        fbNuevaNota = findViewById(id.fbNuevaNota);

        GestionFichero.leerDatos(this);
/*
        listaNotas.add(new Nota("Comer berenjena", "Normal"));
        listaNotas.add(new Nota("Dormir", "Urgente"));
        listaNotas.add(new Nota("Ir a casa", "Importante"));
        listaNotas.add(new Nota("Dar un paseo", "Importante"));
*/
        //Crear un adapter pasandole el contexto y el arraylist con los datos
        listadoNotasAdapter = new ListadoNotasAdapter(this, listaNotas);

        lstNotas.setAdapter(listadoNotasAdapter);

        fbNuevaNota.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, nuevaNotaActivity.class);
                startActivity(intent);
            }

        });

        lstNotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                createSimpleDialog(position).show();

                return true;
            }
        });

        lstNotas.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(MainActivity.this, ModificarNotaActivity.class);
                intent.putExtra(("nota"), position);
                startActivity(intent);
            }

        });
    }

    public AlertDialog createSimpleDialog(final int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Atención")
                .setMessage("¿Seguro que desea eliminar esta nota?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Eliminar nota
                        listaNotas.remove(position);
                        listadoNotasAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //cancelar operacion
                    }
                });
        return builder.create();
    }
    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case id.it_acercade:
                //TODO: mostrar pantalla acerca de
                Intent intent = new Intent(MainActivity.this, InformacionActivity.class);
                intent.putExtra("texto", R.string.texto_acerca_de);
                intent.putExtra("encabezado", "Acerca de");
                startActivity(intent);
                //Toast.makeText(this, "Has pulsado ACERCA DE", Toast.LENGTH_LONG).show();
                return true;
            case id.it_privacidad:
                //TODO: mostrar pantalla politica de privacidad
                Intent intent2 = new Intent(MainActivity.this, InformacionActivity.class);
                intent2.putExtra("texto", R.string.PoliticaPrivacidad);
                intent2.putExtra("encabezado", "Privacidad");
                startActivity(intent2);
                finish();
               //Toast.makeText( this, "Has pulsado PRIVACIDAD", Toast.LENGTH_LONG).show();
                return true;
            case id.it_sincronizar:
                Toast.makeText(this, "Has pulsado SINCRONIZA", Toast.LENGTH_LONG).show();
                //TODO: sincronizar datos
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // Fin menu
    protected void onStart()
    {
        super.onStart();
        listadoNotasAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GestionFichero.guardarDatos(this);
    }
}

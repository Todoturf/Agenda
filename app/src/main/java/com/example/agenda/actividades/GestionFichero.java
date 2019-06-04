package com.example.agenda.actividades;

import android.content.Context;
import android.widget.Toast;

import com.example.agenda.datos.ListaDatos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionFichero
{
    public static void guardarDatos(Context context)
    {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput("notas.txt", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ListaDatos.listaNotas);
            oos.close();
            fos.close();
            Toast.makeText(context, "Guardando datos", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void leerDatos(Context context)
    {
        try
        {
            FileInputStream fis = context.openFileInput("notas.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ListaDatos.listaNotas = (ArrayList)ois.readObject();
            ois.close();
            fis.close();
            Toast.makeText(context, "Leyendo datos", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

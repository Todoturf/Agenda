package com.example.agenda.datos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Nota implements Serializable
{
    private Calendar fecha;
    private String texto;
    private String categoria;
    private static  String[] categorias = {"Urgente", "Importante", "Normal"};



    public Nota(String texto, String categoria) {
        this.fecha = Calendar.getInstance();
        this.texto = texto;
        this.categoria = categoria;
    }

    public Nota(Calendar fecha, String texto, String categoria) {
        this.fecha = fecha;
        this.texto = texto;
        this.categoria = categoria;
    }

    public String comprobarCategoria(String cat)
    {
        String categoriaAux = "Normal";
        for (int i = 0; i < categorias.length; i++)
        {
            if (cat.equalsIgnoreCase(categorias[i]))
                categoriaAux = categorias[i];
        }
        return categoriaAux;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static String[] getCategorias() {
        return categorias;
    }

    public static void setCategorias(String[] categorias) {
        Nota.categorias = categorias;
    }

    public int getIndexCategoria()
    {
        int aux = 0;
        for (int i = 0; i < categorias.length; i++)
        {
            if (this.categoria.equalsIgnoreCase(categorias[i]))
            {
                aux = i;
            }
        }
        return aux;
    }

    public String getFechatoString()
    {
        String myFormat = "dd MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        return sdf.format(this.fecha.getTime());
    }

    @Override
    public String toString() {
        return "Nota{" +
                "fecha=" + fecha +
                ", texto='" + texto + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}

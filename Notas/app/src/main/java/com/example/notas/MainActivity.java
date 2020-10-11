package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.notas.Entity.Actividades;
import com.example.notas.Entity.Corte;
import com.example.notas.Entity.Materia;
import com.example.notas.Entity.Promedio;
import com.example.notas.Operaciones.ManejoArchivos;
import com.google.android.material.tabs.TabLayout;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE = "com.example.notas.MESSAGE" ;
    public static ArrayList<Promedio> listadoPromedios = new ArrayList<>();


    private Button BtnCalcular;
    private TextView TxtIdPromedio,TxtNombre;
    private TableLayout tableLayout;
    private  TableDynamic tableDynamic;


    private String stringPromedio,stringNombre;
    private String[] header={"Id","Nombre ", "Promedio"};
    private ArrayList<String[]> rows= new ArrayList<>();


    private ManejoArchivos serviceArchivos= new ManejoArchivos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapearTabla();
    }


    public void llenarCorte(View view){

        stringNombre = "";
        TxtNombre=findViewById(R.id.TxtNombre);
        stringNombre=TxtNombre.getText().toString();

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, stringNombre);
        DisplayMessageActivity.promedios=serviceArchivos.verPromedios("Promedios",getApplicationContext());
        startActivity(intent);
    }


    public void agregarPromedio(View view) {
        obtenerDatos();
    }

        private void abrirFormulario(){

            Intent intent = new Intent(this, DisplayMessageActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "Formulario de Actividades");
            startActivity(intent);

        }

        private void obtenerDatos(){

            stringNombre = "";
            stringPromedio="";

            TxtIdPromedio=findViewById(R.id.TxtIdPromedio);
            stringPromedio=TxtIdPromedio.getText().toString();

            TxtNombre=findViewById(R.id.TxtNombre);
            stringNombre=TxtNombre.getText().toString();

            listadoPromedios.clear();

            Promedio promedioNuevo=new Promedio(stringPromedio,stringNombre);

            listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());

            listadoPromedios.add(promedioNuevo);

            serviceArchivos.agregar(listadoPromedios,"Promedios",getApplicationContext());

            addItem();

        }



        private void mapearTabla(){
            tableLayout=(TableLayout) findViewById(R.id.tablaPromedios);
            tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
            tableDynamic.addHeader(header);
            tableDynamic.addData(getPromedios());
        }

        private ArrayList<String[]> getPromedios() {


            listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());

            for (Promedio promedio:
                 listadoPromedios) {
                rows.add(new String[]{promedio.getIdPonderado(), promedio.getNombreEstudiante(), ""+promedio.ponderado()});
            }

            return rows;
        }

        private void addItem(){

            String[] item=new String[]{};

            listadoPromedios.clear();
            listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());

            for (Promedio promedio:
                    listadoPromedios) {
                 item = new String[]{promedio.getIdPonderado(), promedio.getNombreEstudiante(), ""+promedio.ponderado()};
            }

            tableDynamic.addItems(item);

        }








    private void calcular(){

        ArrayList<Actividades> actividadesList=new ArrayList<>();

        Actividades actividad= new Actividades("Quiz",40,3.6);
        Actividades actividads= new Actividades("Parcial",60,4.0);




        actividadesList.add(actividad);
        actividadesList.add(actividads);

        Materia materias = new Materia();



        materias.setActividadesCorte(actividadesList);


        System.out.println(materias.calcularDefinitiva());


        Actividades A2Materiados= new Actividades("ads",30,4.0);
        Actividades A1Materiados= new Actividades("ads",60,5.0);
        Actividades A3Materiados= new Actividades("quiz",10,2.5);



        actividadesList=new ArrayList<>();

        actividadesList.add(A2Materiados);
        actividadesList.add(A1Materiados);
        actividadesList.add(A3Materiados);

        Materia materiados = new Materia();


        materiados.setActividadesCorte(actividadesList);


        System.out.println(materiados.calcularDefinitiva());


        ArrayList<Materia> materiasList=new ArrayList<>();

        materiasList.add(materias);
        materiasList.add(materiados);

        Corte corte = new Corte();

        corte.setCorte("1");
        corte.setListadoMateria(materiasList);


        Log.d("Filtro", ""+corte.calcularPromedio());

        ArrayList<Corte> cortes = new ArrayList<>();

        cortes.add(corte);

        Promedio pro=new Promedio();

        pro.setListCortes(cortes);

        Log.d("Filtro", ""+pro.ponderado());


    }



}
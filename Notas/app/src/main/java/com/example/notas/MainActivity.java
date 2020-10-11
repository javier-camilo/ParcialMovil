package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.notas.Entity.Actividades;
import com.example.notas.Entity.Corte;
import com.example.notas.Entity.Materia;
import com.example.notas.Entity.Promedio;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcular();
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
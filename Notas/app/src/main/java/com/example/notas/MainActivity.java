package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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



        }



        private void mapearTabla(){

            tableLayout=(TableLayout) findViewById(R.id.tablaPromedios);
            TableDynamic tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
            tableDynamic.addHeader(header);
            tableDynamic.addData(getPromedios());

        }

        private ArrayList<String[]> getPromedios() {


            listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());

            for (Promedio promedio:
                 listadoPromedios) {
                rows.add(new String[]{promedio.getIdPonderado(), promedio.getNombreEstudiante(), "0.0"});
            }

            return rows;
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

    public  void Operacion(){

        /*
        TxtNumero = findViewById(R.id.TxtNumero);
        TxtNumeroDos = findViewById(R.id.TxtNumeroDos);

        double number = Double.parseDouble(TxtNumero.getText().toString());
        double Dos = Double.parseDouble(TxtNumeroDos.getText().toString());

        double calcular = 0;

        calcular = number + Dos;


        BtnCalcular = findViewById(R.id.BtnCalcular);


         */

    }



    //************************************************crud Promedios****************************************************

    //guardado promedio
    public void agregar(ArrayList<Promedio> listadoPromedios,String nombre){

        ObjectOutputStream escritor=null;

        try {
            File archivo= new File(getFilesDir()+""+nombre);
            System.out.println(getFilesDir()+""+nombre);
            //crear un archivo si no existe
            if(!(archivo.exists())){
                try {
                    archivo.createNewFile();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //escritura del archivo

            FileOutputStream file=new FileOutputStream(archivo);
            escritor= new ObjectOutputStream(file);
            escritor.writeObject(listadoPromedios);

           Log.d("avisos", "se creo correctamente");

        } catch (IOException error) {

            Log.d("avisos", "error al crear el archivo");

        }finally{
            try {
                if(escritor!=null){
                    escritor.close();
                }
            } catch (IOException error) {

            }
        }


    }

    //consulta promedio
    public ArrayList<Promedio> ver(String nombre){
        //lectura

        ObjectInputStream lector=null;
        ArrayList<Promedio> listado=new ArrayList<>();
        File archivo;

        try {
            //lectura del archivo binario

            archivo=new File(getFilesDir()+""+nombre);
            FileInputStream file=new FileInputStream(archivo);
            lector=new ObjectInputStream(file);

            //obtencion del listado

            listado=(ArrayList<Promedio>) lector.readObject();

        } catch (Exception e) {

        }finally{
            if(lector!=null){
                try{
                    lector.close();
                }catch(IOException error){

                }
            }
        }

        return listado;
    }

    //eliminar
    public void Eliminar(){

    }





}
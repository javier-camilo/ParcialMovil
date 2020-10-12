package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

    private ListView listview;
    private ArrayList<String> names;


    private ManejoArchivos serviceArchivos= new ManejoArchivos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());

        for (Promedio promedio:listadoPromedios) System.out.println(promedio.getNombreEstudiante());

        listMapear();


    }


    public void llenarCorte(View view){

        stringPromedio="";

        TxtIdPromedio=findViewById(R.id.TxtIdPromedio);

        stringPromedio=TxtIdPromedio.getText().toString();

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, stringPromedio);
        DisplayMessageActivity.promedios=serviceArchivos.verPromedios("Promedios",getApplicationContext());
        startActivity(intent);

    }


    public void agregarPromedio(View view) {

        stringNombre ="";
        stringPromedio="";

        TxtIdPromedio=findViewById(R.id.TxtIdPromedio);
        stringPromedio=TxtIdPromedio.getText().toString().trim();

        TxtNombre=findViewById(R.id.TxtNombre);
        stringNombre=TxtNombre.getText().toString().trim();

        if(stringNombre.equals("") || stringPromedio.equals("")){
        }else{
            obtenerDatos();
            limpiar();
        }
    }

        private  void limpiar(){
            TxtNombre.setText("");
            TxtIdPromedio.setText("");
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

            int encontrado=0;

            for ( Promedio p:listadoPromedios) {
                if(p.getIdPonderado().equals(stringPromedio)){
                    encontrado=1;
                }
            }

            if(encontrado==1){

                Log.d("Prueba", "ya se encuentra registrado");

            }else{

                listadoPromedios.add(promedioNuevo);

                serviceArchivos.agregar(listadoPromedios,"Promedios",getApplicationContext());

                listMapear();

            }
        }


        private void mapearTabla(){

                /*
                    tableLayout=(TableLayout) findViewById(R.id.tablaPromedios);
                    tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
                    tableDynamic.addHeader(header);
                    tableDynamic.addData(getPromedios());

                 */
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


        private void listMapear(){

            listview = (ListView) findViewById(R.id.listview);
            names = new ArrayList<String>();

            listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());

            for (Promedio promedio:
                    listadoPromedios) {
                names.add("ID: "+promedio.getIdPonderado()+" Nombre: "+promedio.getNombreEstudiante()+"  Nota: "+promedio.ponderado());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
            listview.setAdapter(adapter);


        }


    public void borrarTodo(View view){
            listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());
            listadoPromedios.clear();
            serviceArchivos.agregar(listadoPromedios,"Promedios",getApplicationContext());
            listMapear();
        }

    public void BorraUno(View view) {



        stringPromedio="";

        TxtIdPromedio=findViewById(R.id.TxtIdPromedio);
        stringPromedio=TxtIdPromedio.getText().toString();

        if(stringPromedio.equals("")){
            Log.d("Prueba","debe digitar la id a eliminar");
        }else{

            listadoPromedios.clear();
            listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());

            int lugar = buscar() - 1;

            listadoPromedios.remove(lugar);
            serviceArchivos.agregar(listadoPromedios, "Promedios",getApplicationContext());

            listMapear();
        }

    }

    private int buscar(){

        stringPromedio="";

        TxtIdPromedio=findViewById(R.id.TxtIdPromedio);
        stringPromedio=TxtIdPromedio.getText().toString();

        int index=0;

        for (Promedio pro:listadoPromedios){

            index++;

            if(pro.getIdPonderado().equals(stringPromedio)){
                return index;
            }
        }
        return 0;
    }


    public void Modificar(View view){

        int pos=buscar()-1;

        listadoPromedios.clear();

        listadoPromedios = serviceArchivos.verPromedios("Promedios",getApplicationContext());


        stringNombre = "";
        stringPromedio="";

        TxtIdPromedio=findViewById(R.id.TxtIdPromedio);
        stringPromedio=TxtIdPromedio.getText().toString();

        TxtNombre=findViewById(R.id.TxtNombre);
        stringNombre=TxtNombre.getText().toString();

        listadoPromedios.get(pos).setNombreEstudiante(stringNombre);
        listadoPromedios.get(pos).setIdPonderado(stringPromedio);

        serviceArchivos.agregar(listadoPromedios,"Promedios",getApplicationContext());

        listMapear();

    }







}
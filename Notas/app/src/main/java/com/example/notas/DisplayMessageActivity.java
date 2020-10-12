package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.notas.Entity.Actividades;
import com.example.notas.Entity.Corte;
import com.example.notas.Entity.Materia;
import com.example.notas.Entity.Promedio;
import com.example.notas.Operaciones.ManejoArchivos;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {



    public static ArrayList<Promedio> promedios =new ArrayList<>();
    public static ArrayList<Materia> materias = new ArrayList<>();
    public static ArrayList<Corte> cortes=new ArrayList<>();
    public static ArrayList<Actividades> actividades=new ArrayList<>();

    private ArrayList<String> names;

    private ListView listview;
    private  TextView textView, TxtNota, TxtMateria, TxtActividad, TxtPorcentaje, TxtCorte;

    private static String  txtMateria, txtActividad, txtCorte;
    private static double txtPorcentaje,txtNota;

    private static Corte corteUno=new Corte();
    private static Corte corteDos=new Corte();
    private static Corte corteTres=new Corte();
    private static Actividades actividadNueva=new Actividades();
    private static Materia materia=new Materia();

    private ManejoArchivos serviceArchivos=new ManejoArchivos();





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        obteniendoDatos();

        corteUno.setCorte("1");
        corteDos.setCorte("2");
        corteTres.setCorte("3");


        for (Promedio pro: promedios){
            System.out.println(pro.getNombreEstudiante());
        }

        calcular();


    }


    public void agregar(View view){
        agregarActividad();
        System.out.println("hola");
    }

        private void agregarActividad(){


            llenar();
            iniciaCortes();
            actividadNueva= new Actividades();
            actividadNueva.setActividad(txtActividad);
            actividadNueva.setPorcentaje(txtPorcentaje);
            actividadNueva.setNota(txtNota);
            actividades.add(actividadNueva);

            materia.setActividadesCorte(actividades);
            materias.add(materia);

            actualizarCortes();

            listMapper();

        }

        private void actualizarCortes(){

            String valor= textView.getText().toString();

            for (Promedio p: promedios) {

                if (p.getIdPonderado().equals(valor)) {

                    for (Corte c : p.getListCortes()) {

                        if (txtCorte.equals("1")) {
                            corteUno.setListadoMateria(materias);
                        } else if (txtCorte.equals("2")) {
                            corteDos.setListadoMateria(materias);
                        } else if (txtCorte.equals("3")) {
                            corteTres.setListadoMateria(materias);
                        }

                    }

                }


            }

            serviceArchivos.agregar(promedios,"Promedios",getApplicationContext());
        }

        private void llenar(){

            TxtNota = findViewById(R.id.TxtNota);
            TxtMateria=findViewById(R.id.TxtMateria);
            TxtActividad=findViewById(R.id.TxtActividad);
            TxtPorcentaje=findViewById(R.id.TxtPorcentaje);
            TxtCorte=findViewById(R.id.TxtCorte);

            txtMateria=TxtMateria.getText().toString();
            txtActividad=TxtActividad.getText().toString();
            txtCorte=TxtCorte.getText().toString();
            txtPorcentaje=Double.parseDouble(TxtPorcentaje.getText().toString());
            txtNota=Double.parseDouble(TxtNota.getText().toString());


        }


    private ArrayList<Corte> listadoCorte(){

        ArrayList<Corte> cortesList=new ArrayList<>();

        String valor= textView.getText().toString();

        for (Promedio promedio:
                promedios) {
            if(promedio.getIdPonderado().equals(valor)) {
                cortesList=promedio.getListCortes();
            }
        }

        return cortesList;

    }

    private void listMapper(){

        serviceArchivos.verPromedios("Promedios",getApplicationContext());
        listview = (ListView) findViewById(R.id.listviewTwo);
        names = new ArrayList<String>();

        try {

            for (Corte corte:
                    listadoCorte()) {

                for ( Materia materia: corte.getListadoMateria()){

                    for (Actividades actividad: materia.getActividadesCorte()){

                        names.add("Actividad: "+actividad.getActividad()+" porcentaje: "+actividad.getNota()+"  Materia: "+materia.getNombreMateria()
                        +"corte: "+ corte.getCorte());

                    }
                }

            }

        }catch (Exception e){

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);

    }

    private void iniciaCortes(){
        for (Corte corte:listadoCorte()){
            if(corte.getCorte().equals("1")){
                corteUno=corte;
            }else if(corte.getCorte().equals("2")){
                corteDos=corte;
            }else if(corte.getCorte().equals("3")){
                corteTres=corte;
            }
        }
    }



    public void obteniendoDatos() {

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
         textView = findViewById(R.id.TxtMensaje);
        textView.setText(message);

    }

    public void mandar(View view){

    }


    private void calcular(){

        ArrayList<Actividades> actividadesList=new ArrayList<>();

        Actividades actividad= new Actividades("Quiz",40,3.6);
        Actividades actividads= new Actividades("Parcial",60,4.0);



        actividadesList.add(actividad);
        actividadesList.add(actividads);



        Materia materias = new Materia();



        materias.setActividadesCorte(actividadesList);


        Log.d("Prueba","definitiva de la materia: "+materias.calcularDefinitiva());


        Actividades A2Materiados= new Actividades("ads",30,4.0);
        Actividades A1Materiados= new Actividades("ads",60,5.0);
        Actividades A3Materiados= new Actividades("quiz",10,2.5);



        actividadesList=new ArrayList<>();

        actividadesList.add(A2Materiados);
        actividadesList.add(A1Materiados);
        actividadesList.add(A3Materiados);

        Materia materiados = new Materia();


        materiados.setActividadesCorte(actividadesList);


        Log.d("Prueba","definitiva de la materia: "+materiados.calcularDefinitiva());


        ArrayList<Materia> materiasList=new ArrayList<>();

        materiasList.add(materias);
        materiasList.add(materiados);

        Corte corte = new Corte();

        corte.setCorte("1");
        corte.setListadoMateria(materiasList);


        Log.d("Prueba", ""+corte.calcularPromedio());

        ArrayList<Corte> cortes = new ArrayList<>();

        cortes.add(corte);

        Promedio pro=new Promedio();

        pro.setListCortes(cortes);

        Log.d("Prueba", " corteUno: "+corte.calcularPromedio()+" corte dos: 0"+" Corte tres: 0"+" Definitiva: "+pro.ponderado());


    }




}
package com.example.notas.Operaciones;

import android.content.Context;
import android.util.Log;

import com.example.notas.Entity.Actividades;
import com.example.notas.Entity.Materia;
import com.example.notas.Entity.Promedio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ManejoArchivos {

    public void agregar(ArrayList<Promedio> listadoPromedios, String nombre, Context context){

        ObjectOutputStream escritor=null;

        try {
            File archivo= new File(context.getFilesDir()+""+nombre);
            System.out.println(context.getFilesDir()+""+nombre);
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

    public ArrayList<Promedio> verPromedios(String nombre, Context context){
        //lectura

        ObjectInputStream lector=null;
        ArrayList<Promedio> listado=new ArrayList<>();
        File archivo;

        try {
            //lectura del archivo binario

            archivo=new File(context.getFilesDir()+""+nombre);
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


    public void agregarCalculador(Materia materia, String nombre, Context context){

        ObjectOutputStream escritor=null;

        try {
            File archivo= new File(context.getFilesDir()+""+nombre);
            System.out.println(context.getFilesDir()+""+nombre);
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
            escritor.writeObject(materia);

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

    public Materia verMateria(String nombre, Context context){
        //lectura

        ObjectInputStream lector=null;
        Materia materia=new Materia();
        File archivo;

        try {
            //lectura del archivo binario

            archivo=new File(context.getFilesDir()+""+nombre);
            FileInputStream file=new FileInputStream(archivo);
            lector=new ObjectInputStream(file);

            //obtencion del listado

            materia=(Materia) lector.readObject();

        } catch (Exception e) {

        }finally{
            if(lector!=null){
                try{
                    lector.close();
                }catch(IOException error){

                }
            }
        }

        return materia;
    }





}

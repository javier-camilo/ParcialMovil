package com.example.notas.Entity;

import android.util.Log;

import java.util.ArrayList;

public class Corte {


    private ArrayList<Materia> listadoMateria;
    private String corte;
    private double promedioCorte;

    public double getPromedioCorte() {
        return promedioCorte;
    }

    public void setPromedioCorte(double promedioCorte) {
        this.promedioCorte = promedioCorte;
    }

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }


    public ArrayList<Materia> getListadoMateria() {
        return listadoMateria;
    }

    public void setListadoMateria(ArrayList<Materia> listadoMateria) {
        this.listadoMateria = listadoMateria;
    }

    public Materia getMateria(String idMateria){

        Materia materiaObtenida= null;

        for (Materia materia: listadoMateria) {
            if (materia.getIdMateria()==idMateria){
                materiaObtenida=materia;
            }
        }

        return materiaObtenida;
    }

    public double calcularPromedio(){

        double sumador=0,contador=0;

        for (Materia materia: listadoMateria) {
            sumador+=materia.calcularDefinitiva();
            contador++;
        }

        return sumador/contador;
    }





}

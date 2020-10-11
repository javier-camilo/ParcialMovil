package com.example.notas.Entity;

import java.util.ArrayList;

public class Materia {

    private String idMateria;
    private String nombreMateria;
    private ArrayList<Actividades> actividadesCorte;
    private double definitivaMateria;

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public double getDefinitivaMateria() {
        return definitivaMateria;
    }

    public void setDefinitivaMateria(double definitivaMateria) {
        this.definitivaMateria = definitivaMateria;
    }

    public ArrayList<Actividades> getActividadesCorte() {
        return actividadesCorte;
    }

    public void setActividadesCorte(ArrayList<Actividades> actividadesCorte) {
        this.actividadesCorte = actividadesCorte;
    }

    public double calcularDefinitiva(){

        definitivaMateria=0;

        for (Actividades actividades: actividadesCorte) {
            definitivaMateria=definitivaMateria+actividades.valorPorcentual();
        }

        return definitivaMateria;
    }



}

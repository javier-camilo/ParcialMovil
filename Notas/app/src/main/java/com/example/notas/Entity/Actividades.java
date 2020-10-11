package com.example.notas.Entity;

public class Actividades {

    private String actividad;
    private double porcentaje;
    private double nota;

    public Actividades(String actividad, double porcentaje, double nota) {
        this.actividad = actividad;
        this.porcentaje = porcentaje;
        this.nota = nota;
    }

    public Actividades() {

    }

    public double valorPorcentual() {
        double valorNota=0;
        valorNota=porcentaje/100;
        return valorNota*nota;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }


}

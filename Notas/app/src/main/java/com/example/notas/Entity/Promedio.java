package com.example.notas.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Promedio  implements Serializable  {


    public String idPonderado;
    public String nombreEstudiante;
    private ArrayList<Corte> listCortes;

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public ArrayList<Corte> getListCortes() {
        return listCortes;
    }

    public void setListCortes(ArrayList<Corte> listCortes) {
        this.listCortes = listCortes;
    }

    public String getIdPonderado() {
        return idPonderado;
    }

    public void setIdPonderado(String idPonderado) {
        this.idPonderado = idPonderado;
    }

    public Corte getCorte(String corteBuscado){

        Corte corteObtenido= null;

        for (Corte corte: listCortes) {
            if (corte.getCorte()==corteBuscado){
                corteObtenido=corte;
            }
        }
        return corteObtenido;

    }

    public double ponderado(){

        double corteUno=0,corteDos=0, corteTres=0;

        for (Corte corte: listCortes) {

            if(corte.getCorte()=="1"){

               corteUno=corte.calcularPromedio() * 0.3 ;

            }else if(corte.getCorte()=="2"){

                corteDos=corte.calcularPromedio() * 0.3 ;

            }else if(corte.getCorte()=="3"){

                corteTres=corte.calcularPromedio() * 0.4 ;

            }

        }

        return corteUno+corteDos+corteTres;
    }
    

}

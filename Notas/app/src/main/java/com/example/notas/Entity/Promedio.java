package com.example.notas.Entity;

import java.util.ArrayList;

public class Promedio {


    public String idPonderado;
    private ArrayList<Corte> listCortes;


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
    

}

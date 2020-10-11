package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.notas.Entity.Actividades;

public class DisplayMessageActivity extends AppCompatActivity {

    public static Actividades actividad=new Actividades();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        obteniendoDatos();


    }

    public void obteniendoDatos(){

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView  TxtMessage=  findViewById(R.id.TxtMensaje);

        TxtMessage.setText(actividad.getActividad());

    }

    public void mandar(View view){

        MainActivity.mostrarAca="pasando de hijo a padre";

    }


}
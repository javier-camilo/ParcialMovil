package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.notas.Entity.Actividades;
import com.example.notas.Entity.Promedio;

public class DisplayMessageActivity extends AppCompatActivity {

    public static Promedio promedio =new Promedio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        obteniendoDatos();


    }

    public void obteniendoDatos(){


    }

    public void mandar(View view){


    }


}
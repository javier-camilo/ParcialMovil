package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.notas.Entity.Actividades;
import com.example.notas.Entity.Promedio;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {



    public static ArrayList<Promedio> promedios =new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        obteniendoDatos();

        for (Promedio pro: promedios){
            System.out.println(pro.getNombreEstudiante());
        }


    }

    public void obteniendoDatos() {

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.TxtMensaje);
        textView.setText(message);

    }

    public void mandar(View view){


    }


}
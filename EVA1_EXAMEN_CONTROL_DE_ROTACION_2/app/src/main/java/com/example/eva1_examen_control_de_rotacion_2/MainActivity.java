package com.example.eva1_examen_control_de_rotacion_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String mostrar ="The Space Shuttle is a partially reusable low Earth orbital spacecraft system that was operated from 1981 to 2011 by the U.S. National Aeronautics and Space Administration (NASA) as part of the Space Shuttle program. Its official program name was Space Transportation System (STS), taken from a 1969 plan for a system of reusable spacecraft of which it was the only item funded for development.";

        texto = findViewById(R.id.texto);
        texto.setText(mostrar);
    }
}

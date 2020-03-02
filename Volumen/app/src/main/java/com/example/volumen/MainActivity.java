package com.example.volumen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    SeekBar barangulo;
    Button btncalcular;
    EditText editradio;
    TextView viewangulo;
    LinearLayout hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Invocamos los recursos
        barangulo = findViewById(R.id.BarAngulo);
        viewangulo = findViewById(R.id.Txtangulo);
        editradio = findViewById(R.id.Radionum);
        btncalcular = findViewById(R.id.BtnCal);
        hide = findViewById(R.id.layoutapp);
        editradio.setText("0");
        //Se crean los limites
        barangulo.setProgress(0);
        barangulo.setMax(360);
        // Configurar el seekbar con nombre barangulo
        barangulo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Vizualiza el progreso de la barra es un text view
                viewangulo.setText(progress + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar barangulo) {

            }
        });
    }

    //Creamos el evento on click de boton calcular
    public void Calcularesfera(View view) {
        //Capturamos los valores
        String radio = editradio.getText().toString();
        String angulo = viewangulo.getText().toString();
        //Los comvertimos a variables numericas

        double rd = Double.parseDouble(radio);
        double ag = Double.parseDouble(angulo);

        //Si no da un valor y presiona calcular le mandara un mensaje
        if (rd == 0)   {

            Toast.makeText(this, "Introduzca radio", Toast.LENGTH_SHORT).show();
        }if (ag == 0)   {

            Toast.makeText(this, "Introduzca ángulo", Toast.LENGTH_SHORT).show();
        }

        //si tienes valores resolvera el volumen
else{
            double V = ((2.0 / 3.0) * (rd * ag * 3.0));
            //Mostrar resultado en un Toast
            Toast.makeText(this, "El volúmen es: " + V, Toast.LENGTH_LONG).show();
        }
    }
        //sirve para cerrar el teclado presionando cualquier parte de la pantalla
    //creamos evento on click en el layout principal
        public void Cerrarteclado (View view){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editradio.getWindowToken(), 0);
        }

}


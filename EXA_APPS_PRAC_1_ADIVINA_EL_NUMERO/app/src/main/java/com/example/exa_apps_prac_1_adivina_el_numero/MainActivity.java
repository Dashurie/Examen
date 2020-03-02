package com.example.exa_apps_prac_1_adivina_el_numero;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{

    EditText edTxtF;
    TextView txVw1, txVw2, txVw3, txVw4, txVw5;
    RadioGroup rdGrpSerie;
    RadioButton rdBtnAri, rdBtnAlg;
    Button btnVerify;
    int a, r, c=1, d, empty;
    int series[] = new int[5];
    Random n = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edTxtF = findViewById(R.id.edTxtF);
        txVw1 = findViewById(R.id.txtVw1);
        txVw2 = findViewById(R.id.txtVw2);
        txVw3 = findViewById(R.id.txtVw3);
        txVw4 = findViewById(R.id.txtVw4);
        txVw5 = findViewById(R.id.txtVw5);
        btnVerify = findViewById(R.id.btnVerify);
        rdGrpSerie = findViewById(R.id.rdGrpSerie);
        rdBtnAlg = findViewById(R.id.rdBtnAlg);
        rdBtnAri = findViewById(R.id.rdBtnAri);
        btnVerify.setOnClickListener(this);
        rdGrpSerie.setOnCheckedChangeListener(this);
    }

    public void algebraicSeries(){
        cleanAll();
        //Aleatorios de la formula
        d = n.nextInt(100)+1;
        a = n.nextInt(100)+1;
        //Espacio de la serie que sera aleatorio
        empty = n.nextInt(4);

        series[0] = a;//Asigna el primer valor al primer espacio del vector
        //Rellenar el vector a partir del primer valor
        for(int i=0; i<4; i++){
            series[i+1] = series[i] + (series.length - 1) * d;
        }
        int j = 0;//j para saber que espacio dejar vacío y cada if para comprobar cual quedara vacío
        if(j!=empty){
            txVw1.setText(""+series[0]);
        }else if(j==empty)
            txVw1.setText("__");
        j++;
        if(j!=empty){
            txVw2.setText(""+series[1]);
        }else if(j==empty)
            txVw2.setText("__");
        j++;
        if(j!=empty){
            txVw3.setText(""+series[2]);
        }else if(j==empty)
            txVw3.setText("__");
        j++;
        if(j!=empty){
            txVw4.setText(""+series[3]);
        }else if(j==empty)
            txVw4.setText("__");
        j++;
        if(j!=empty){
            txVw5.setText(""+series[4]);
        }else if(j==empty)
            txVw5.setText("__");
        j++;
    }

    public void arithmethicSeries(){
        cleanAll();

        r = n.nextInt(4)+2;
        a = n.nextInt(5)+1;
        empty = n.nextInt(4);

        series[0] = a;
        for(int i=0; i<4; i++){
            series[i+1] = series[i] * ((int)Math.pow(r,series.length-1));
        }
        int j = 0;
        if(j!=empty){
            txVw1.setText(""+series[0]);
        }else if(j==empty)
            txVw1.setText("__");
        j++;
        if(j!=empty){
            txVw2.setText(""+series[1]);
        }else if(j==empty)
            txVw2.setText("__");
        j++;
        if(j!=empty){
            txVw3.setText(""+series[2]);
        }else if(j==empty)
            txVw3.setText("__");
        j++;
        if(j!=empty){
            txVw4.setText(""+series[3]);
        }else if(j==empty)
            txVw4.setText("__");
        j++;
        if(j!=empty){
            txVw5.setText(""+series[4]);
        }else if(j==empty)
            txVw5.setText("__");
        j++;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.rdBtnAlg){
            algebraicSeries();
        }else{
            arithmethicSeries();
        }
    }

    public void verify(){
        //Primer if para comprobar que el edittext no este vacío
        if(edTxtF.getText().length()!=0) {
            //f para saber que espacio esta vacío y comparar el valor que se ingresa con el que falta
            int f = Integer.parseInt(edTxtF.getText().toString());//Convertir el dato ingresado a numero
            if (f == series[empty]) {//comprobar si el valor ingresado (f) es igual que el faltante en la serie (empty)
                Toast.makeText(this, "Enhorabuena, has acertado", Toast.LENGTH_LONG).show();
                int re = empty + 1; //Switch para agregar el valor a la serie y resaltar el color
                switch (re) {
                    case 1:
                        txVw1.setText("" + f);
                        txVw1.setTextColor(Color.parseColor("#F60505"));
                        break;
                    case 2:
                        txVw2.setText("" + f);
                        txVw2.setTextColor(Color.parseColor("#F60505"));
                        break;
                    case 3:
                        txVw3.setText("" + f);
                        txVw3.setTextColor(Color.parseColor("#F60505"));
                        break;
                    case 4:
                        txVw4.setText("" + f);
                        txVw4.setTextColor(Color.parseColor("#F60505"));
                        break;
                    case 5:
                        txVw5.setText("" + f);
                        txVw5.setTextColor(Color.parseColor("#F60505"));
                        break;
                    default:
                        break;
                }
                c = 1;
            } else {
                if (c == 3) {
                    Toast.makeText(this, "Has fallado todos los intentos. Intentalo de nuevo", Toast.LENGTH_LONG).show();
                    //Llamada a metodo para reiniciar
                    cleanAll();
                    c = 1;
                } else {
                    //Conteo de intentos
                    Toast.makeText(this, "Incorrecto. Te queda(n) " + (3 - c) + " intento(s)", Toast.LENGTH_LONG).show();
                    c++;
                }
            }
        }else{
            //Mensaje de edittext vacío
            Toast.makeText(this,"Por favor ingresa un dato",Toast.LENGTH_LONG).show();
        }

    }

    //Metodo para reiniciar
    public void cleanAll(){
        txVw1.setText("___");
        txVw2.setText("___");
        txVw3.setText("___");
        txVw4.setText("___");
        txVw5.setText("___");
        edTxtF.setText("");

        txVw1.setTextColor(Color.parseColor("#020202"));
        txVw2.setTextColor(Color.parseColor("#020202"));
        txVw3.setTextColor(Color.parseColor("#020202"));
        txVw4.setTextColor(Color.parseColor("#020202"));
        txVw5.setTextColor(Color.parseColor("#020202"));
    }

    @Override
    public void onClick(View v) {
        verify();
    }
}

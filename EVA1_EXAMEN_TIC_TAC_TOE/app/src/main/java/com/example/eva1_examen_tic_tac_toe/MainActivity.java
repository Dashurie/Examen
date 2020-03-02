package com.example.eva1_examen_tic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{ //se agregó un implements de la clase view para heredar sus métodos

    Button[][] botones = new Button[3][3]; //arreglo para los botones de 3x3
    Button reiniciar;
    TextView texto;

    boolean jugadorX = true; //boolean para saber si es el turno de el jugador X

    int contadorTurnos;//cuenta cuantos turnos se han jugado


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.texto);
        texto.setText("X");//como inicia X se pone el textview en X

        //estos for son para ponerle el findViewById a cada boton porque es un arreglo
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String idBoton = "b" + i + j; //el string es para tener el id de cada boton
                int resID = getResources().getIdentifier(idBoton, "id", getPackageName());//esto obtiene toda la informacion de cada boton
                botones[i][j] = findViewById(resID);//aqui se le pone el findViewById para cada boton usando el resID que esta en la linea de arriba
                botones[i][j].setOnClickListener(this);//el this va a marcar error si no se agrega el implements de la clase View
            }
        }

        reiniciar = findViewById(R.id.reiniciar);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarJuego(); //cuando se le de click al boton REINICIAR se va a ejecutar el metodo reiniciarJuego

            }
        });

    }


    //este es el metodo que se genera cuando se implementa View.OnClickListener y adentro ponemos las acciones que se van a hacer cuando demos click en un boton
    @Override
    public void onClick(View v) {
        /*este if nos dice que si los botones tienen un texto diferente a "-" nos va a regresar un return en el metodo,
        ya que este metodo solo se va a ejecutar para los botones que tengan "-"   */
        if (!((Button) v).getText().toString().equals("-")) {
            return;
        }


        if (jugadorX) {//en este if si es el turno del jugador X

            ((Button) v).setText("X");//se le va a cambiar el texto del boton al que se le de click por X
            ((Button) v).setEnabled(false);// y se va a deshabilitar el boton para que ya no se le pueda volver a dar click
            texto.setText("O");//Una vez que se le dio click ahora es el turno de O se pone el textView en "O"

        } else {//es el turno del jugador O

            ((Button) v).setText("O");//se le va a cambiar el texto del boton al que se le de click por O
            ((Button) v).setEnabled(false);// y se va a deshabilitar el boton para que ya no se le pueda volver a dar click
            texto.setText("X");//Una vez que se le dio click ahora es el turno de X se pone el textView en "X"

        }
        contadorTurnos++;//se van contando los turnos que se han jugado


        if (ganador()) {//si el metodo de ganador() regresa un true
            if (jugadorX) {//si jugadorX esta en true quiere decir que gano X
                ganaX();// y se ejecuta el metodo ganaX()
            } else {//si no entonces quiere decir que jugadorX esta en false, osea que gana O
                ganaO();//y se ejecuta el metodo ganaO()
            }
        }

        //Si el metodo ganador() regresa un false, quiere decir que no gano nadie
        else if (contadorTurnos == 9) {//si el contador llego a 9 (osea que ya le picaron a los 9 botones), es un empate
            empate();//se ejecuta el metodo empate()
        } else {//si todavia no hay ganador o empate quiere decir que aun estan jugando
            jugadorX = !jugadorX;//esto hace que el turno cambie de jugador, para que no siempre sea el turno de el jugador X
            //osea que si jugadorX esta en true, va a cambiar a false, y viceversa
        }



    }


    //este metodo es para saber si ya hay un ganador (NO te dice cual de los 2 gano), si regresa true es que si gano alguien, si regresa false fue un empate
    private boolean ganador() {

        String[][] arreglo = new String[3][3];


        //todos los for son para saber si ya se completo una linea y gano alguien
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arreglo[i][j] = botones[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (arreglo[i][0].equals(arreglo[i][1])
                    && arreglo[i][0].equals(arreglo[i][2])
                    && !arreglo[i][0].equals("-")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (arreglo[0][i].equals(arreglo[1][i])
                    && arreglo[0][i].equals(arreglo[2][i])
                    && !arreglo[0][i].equals("-")) {
                return true;
            }
        }

        if (arreglo[0][0].equals(arreglo[1][1])
                && arreglo[0][0].equals(arreglo[2][2])
                && !arreglo[0][0].equals("-")) {
            return true;
        }

        if (arreglo[0][2].equals(arreglo[1][1])
                && arreglo[0][2].equals(arreglo[2][0])
                && !arreglo[0][2].equals("-")) {
            return true;
        }

        return false;// si regresa un false es porque no hubo ganador y es empate
    }



    //este metodo se ejecuta si gana X
    private void ganaX() {
        texto.setText("GANA X");
        //con los for hace que se deshabiliten todos los botones para que ya no le puedan picar, porque ya gano X
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(false);
            }}

        //aqui se crea un AlertDialog con el nombre de alertView
        AlertDialog alertView = new AlertDialog.Builder(this).create();
        alertView.setTitle("Ganador");
        alertView.setMessage("GANA X");

        //esto es para poner el boton de Ok, se usa el metodo setButton
        alertView.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }});
        alertView.show();//esto hace que se muestre el mensaje
    }





    //este metodo se ejecuta si gana O
    private void ganaO() {
        texto.setText("GANA O");//se cambia el textView a GANA O
        //con los for hace que se deshabiliten todos los botones para que ya no le puedan picar, porque ya gano O
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(false);
            }}

        //aqui se crea un AlertDialog con el nombre de alertView
        AlertDialog alertView = new AlertDialog.Builder(this).create();//el alertDialog hace uso del metodo builder, y le tenemos que poner el contexto de this
        alertView.setTitle("Ganador");
        alertView.setMessage("GANA O");

        //esto es para poner el boton de Ok, se usa el metodo setButton
        alertView.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }});
        alertView.show();//esto hace que se muestre el mensaje

    }





    //este metodo se va a ejecutar si hay empate
    private void empate() {
        texto.setText("EMPATE");//el text view cambia a EMPATE
        //aqui se crea un AlertDialog con el nombre de alertView
        AlertDialog alertView = new AlertDialog.Builder(this).create();//el alertDialog hace uso del metodo builder, y le tenemos que poner el contexto de this
        alertView.setTitle("Sin Ganador");//el titulo del mensaje
        alertView.setMessage("EMPATE");//lo que va a decir el mensaje

        //esto es para poner el boton de Ok, se usa el metodo setButton
        alertView.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}}   );

        alertView.show();//esto hace que se muestre el mensaje

    }





    //este metodo se va a ejecutar cuando se de click en el boton Reiniciar
    private void reiniciarJuego() {
        //Los 2 for hacen que todos los botones se vuelvan a poner con el "-" y que se vuelvan a habilitar
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("-");//pone el texto del boton en "-"
                botones[i][j].setEnabled(true);//vuelve a habilitar el boton para que se le pueda dar click
            }
        }

        contadorTurnos = 0;//vuelve a inicializar el contador en 0 para una nueva partida
        jugadorX = true;//hace que el primer turno sea el jugador X
        texto.setText("X");//se vuelve a poner el textView en X

    }


}

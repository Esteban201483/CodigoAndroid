package com.example.sistema.Fraseodiccionario;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Probando...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify prueba1 parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_credits) {
            Intent intent = new Intent(this,CreditosActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Genera un mensaje Toast que desaparece rapidamente.
     * @param mensaje
     */
    public void showMessage(String mensaje)
    {
        Context contexto = getApplicationContext();
        Toast toast = Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT);
        toast.show();

    }

    /**
     * Llama prueba1 una nueva vista sin destruir la vista actual, de manera que el usuario pueda regresar
     * al menu principal con el boton de retroceder del teléfono.
     * @param clase La clase prueba1 llamar. No tiene que ser una instancia.
     * @param categoria El número de categoria al cual dirige el boton
     */
    public void llamarVista(Class clase, int categoria)
    {
        Intent intent = new Intent(this,clase);
        intent.putExtra("CATEGORIA", "" + categoria);
        startActivity(intent);
    }

    /**
     * Esta función se encarga de determinar prueba1 que nueva vista debe llevarse el usuario. El ID del
     * botón presionado determinara la vista.
     * @param button Boton que va prueba1 solicitar la redirección. Se usa para determinar prueba1 que nueva
     *               vista se va prueba1 llevar al usuario.
     */
    public void redireccionarVista(View button)
    {
        switch (button.getId())
        {
            //Cada vez que se agregue una vista, debe agregarse un case correspondiente al boton
            //que llamará a dicha vista.
            case R.id.btn_informacion:
                llamarVista(casoLetra1.class,1);
                break;
            case R.id.btn_pregunta:
                llamarVista(casoLetra1.class, 2);
                break;
            case R.id.btn_saludo:
                llamarVista(casoLetra1.class, 3);
                break;
            case R.id.btn_despedida:
                llamarVista(casoLetra1.class, 4);
                break;
            case R.id.btn_agradecimiento:
                llamarVista(casoLetra1.class, 5);
                break;
            case R.id.btn_fisico:
                llamarVista(casoLetra1.class, 6);
                break;
            case R.id.btn_emocion:
                llamarVista(casoLetra1.class, 7);
                break;
            case R.id.btn_momento:
                llamarVista(casoLetra1.class, 8);
                break;
            case R.id.btn_accion:
                llamarVista(casoLetra1.class, 9);
                break;
            case R.id.btn_solicitud:
                llamarVista(casoLetra1.class, 10);
                break;
            case R.id.btn_permiso:
                llamarVista(casoLetra1.class, 11);
                break;
            case R.id.btn_actividad:
                llamarVista(casoLetra1.class, 12);
                break;
            case R.id.btn_gusto:
                llamarVista(casoLetra1.class, 13);
                break;
            case R.id.btn_pregunta2:
                llamarVista(casoLetra1.class,14);
                break;
            case R.id.btn_expresion:
                llamarVista(casoLetra1.class,15);
                break;

            default:
                showMessage("No se ha asignado ningun accion a este boton");
        }

    }
}

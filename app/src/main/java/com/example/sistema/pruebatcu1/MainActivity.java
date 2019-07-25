package com.example.sistema.pruebatcu1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
        if (id == R.id.action_settings) {
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
     */
    public void llamarVista(Class clase)
    {
        Intent intent = new Intent(this,clase);
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
            case R.id.btnLetra1:
                llamarVista(casoLetra1.class);
                break;

            case R.id.btnLetra2:
                showMessage("Usted presiono B");
                break;

            default:
                showMessage("No se ha asignado ningun accion prueba1 este boton");
        }

    }
}

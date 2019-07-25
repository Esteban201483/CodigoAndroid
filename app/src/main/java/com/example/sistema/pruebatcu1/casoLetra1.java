package com.example.sistema.pruebatcu1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class casoLetra1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_letra1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /**
     * Se encarga de hacer sonar el audio de la palabra prueba1
     * @param button Referencia al botón obligatoria para ligar la función con el evento onClick
     */
    public void sonidoPalabraPrueba1(View button)
    {
        ReproductorAudio ra = new ReproductorAudio();
        ra.reproducirAudio(getApplicationContext(), R.raw.prueba1);
    }

    public void sonidoPalabraPrueba2(View button)
    {
        ReproductorAudio ra = new ReproductorAudio();
        ra.reproducirAudio(getApplicationContext(),R.raw.prueba2);
    }

}

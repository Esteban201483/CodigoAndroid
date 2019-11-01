package com.example.sistema.Fraseodiccionario;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class casoLetra1 extends AppCompatActivity {

    private int indice_palabra = 0; //Indical cual es la palabra de la categoria actual.
    private int id_audio = 0; //Indica cual es el audio que debe sonar
    private int id_imagen = 0; //Indica cual es la imagen que se debe mostrar
    private int categoria = 0; //Indica la categoria actual
    private String genero = "a"; //Indica el genero. a = mujer, b = hombre.

    private int cantidad_elementos[]; //Indica la cantidad de elemmentos por categoria
    private String categorias[]; //Almacena el nombre de las categorias


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_letra1);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

        inicializarCategorias();

        categoria = Integer.parseInt(getIntent().getStringExtra("CATEGORIA"));
        indice_palabra = 1; //Accede a la primera palabra por default
        setearRecursos();
    }


    public void inicializarCategorias()
    {
        categorias = new String[13];

        categorias[0]  = "Información Personal";
        categorias[1]  = "Preguntas";
        categorias[2]  = "Saludos";
        categorias[3]  = "Despedidas";
        categorias[4]  = "Agradecimiento";
        categorias[5]  = "Estados Fisicos";
        categorias[6]  = "Estados Emocionales";
        categorias[7]  = "Momentos";
        categorias[8]  = "Acciones";
        categorias[9]  = "Solicitudes";
        categorias[10] = "Permisos";
        categorias[11] = "Actividades";
        categorias[12] = "Gustos";
    }

    /**
     * Actualiza el nombre de la categoria
     */
    public void setearCategoria()
    {
        TextView cat = (TextView) findViewById(R.id.txt_categoria);
        cat.setText(categorias[categoria-1]);
    }

    /**
     * Actualiza la imagen.
     */
    public void setearImagen()
    {
        String nombre_imagen = "c" + categoria + "s" + indice_palabra;
        // id_imagen = getResources().getIdentifier(nombre_imagen,"drawable",getPackageName());
    }

    /**
     * Actualiza el audio
     * Despliega un mensaje de error en caso de que el audio no exista, pero no tira la aplicación.
     */
    public void setearAudio()
    {
        String nombre_audio = "c" + categoria + "s" + indice_palabra + genero;
        id_audio = getResources().getIdentifier(nombre_audio,"raw",getPackageName());

        if(id_audio == 0)
            showMessage("Error: El audio " + nombre_audio + " no existe");
    }

    /**
     * Asigna la imagen y audio basandose en la palabra y categoría actual
     */
    public void setearRecursos()
    {
        setearCategoria();
        setearAudio();
        setearImagen();
    }

    /**
     * Cambia el genero de la voz
     * @param switchElement el switch cuyo cambio determina el genero de la voz del audio.
     */
    public void cambioGenero(View switchElement)
    {
        if(genero.equals("b"))
            genero = "a";
        else
            genero = "b";

        setearAudio();
    }


    /**
     * Se encarga de hacer sonar el audio de la palabra.
     * Si el audio no existe simplemente no suena en vez de tirar la aplicación.
     * @param button Referencia al botón obligatoria para ligar la función con el evento onClick
     */
    public void sonarAudio(View button)
    {
        if(id_audio != 0) {
            ReproductorAudio ra = new ReproductorAudio();
            ra.reproducirAudio(getApplicationContext(), id_audio);
        }
    }

    /**
     *
     * todo: logica de retroceso
     * @param button el boton de retroceso
     */
    public void retroceder(View button)
    {
        if(categoria > 1 || indice_palabra > 1)
        {
            indice_palabra--;

            if(indice_palabra == 0) {
                categoria--;
                //indice_palabra = maximos[categoria-1] - 1;
            }
            setearRecursos();
        }
    }

    /**
     * todo: logica de avance
     * @param button El boton de avance
     */
    public void avanzar(View button)
    {
        indice_palabra++;

        setearRecursos();
    }


    /**
     * Despliega un mensaje que se desvanece
     * @param mensaje
     */
    public void showMessage(String mensaje)
    {
        Context contexto = getApplicationContext();
        Toast toast = Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT);
        toast.show();

    }

}

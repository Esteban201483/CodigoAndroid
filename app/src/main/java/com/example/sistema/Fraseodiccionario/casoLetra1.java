package com.example.sistema.Fraseodiccionario;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Despliega una interfaz desde la cual se pueden recorrer todas las palabras de todas las categorias.
 * Cada vez que se cambie de palabra, se actualiza el audio y la imagen a desplegar
 *
 * Notación:
 *
 * Audios:
 *      c[número de categoria]s[número de palabra][genero]<versión de Panama>
 *      donde:
 *      Genero a = voz masculina
 *      Genero b = voz femenina
 *
 *      Versión de panama corresponde a la letra p. Si la tiene agregada, entonces
 *      dicho audio corresponde a un audio de Panama. Esto según el cambio indicado.
 *      Audios pendientes
 *
 * Ilustraciones:
 *      c[número de categoria]s[número de palabra]<versión de Panama>
 *      Versión de panama corresponde a la letra p
*
 * Traducciones:
 *
 *  Importante: Los números de categoria del 1 al 9 NO empiezan con 0. Se empieza a contar
 *  a partir del uno
 */

public class casoLetra1 extends AppCompatActivity {

    private final int cantidad_categorias = 15;

    private int indice_palabra = 0; //Indical cual es la palabra de la categoria actual.
    private int id_audio = 0; //Indica cual es el audio que debe sonar
    private int id_imagen = 0; //Indica cual es la imagen que se debe mostrar
    private int categoria = 0; //Indica la categoria actual
    private String genero = "a"; //Indica el genero. a = mujer, b = hombre.

    private int cantidad_elementos[]; //Indica la cantidad de elemmentos por categoria
    private String categorias[]; //Almacena el nombre de las categorias

    private String traduccion = "";  //Almacena la traducción de la palabra actual
    private ImageView imageView;

    private String version_panama = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_letra1); //Asigna el layout a la actividad
        asignarLongOnclick();
        inicializarCategorias();

        imageView = (ImageView) findViewById(R.id.imageViewIlustracion);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonarAudio();
            }
        });

        imageView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v)
            {
                mostrarTraduccion();

                return true;
            }
        });

        categoria = Integer.parseInt(getIntent().getStringExtra("CATEGORIA"));
        indice_palabra = 1; //Accede a la primera palabra por default
        setearRecursos();
    }

    /**
     * Asigna el metodo que despliega el mensaje de la traduccion cuando se presiona el boton
     * más tiempo de lo normal
     */
    public void asignarLongOnclick()
    {
        /*Button boton_palabra = (Button) findViewById(R.id.btn_palabra);

        boton_palabra.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mostrarTraduccion();
                return true;
            }
        });*/
    }

    /**
     * Método intermediario. Cuando se presiona por largo tiempo el boton se encarga de desplegar
     * el mensaje de la traduccion
     */
    public void mostrarTraduccion()
    {
        showMessage(traduccion);
    }

    public void inicializarCategorias()
    {
        categorias = new String[cantidad_categorias];
        cantidad_elementos = new int[cantidad_categorias];

        categorias[0]  = "Información Personal";
        categorias[1]  = "Preguntas Sí/No";
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
        categorias[13] = "Preguntas";
        categorias[14] = "Expresiones Útiles";

        cantidad_elementos[0] = 14;
        cantidad_elementos[1] = 2;
        cantidad_elementos[2] = 30;
        cantidad_elementos[3] = 8;
        cantidad_elementos[4] = 1;
        cantidad_elementos[5] = 8;
        cantidad_elementos[6] = 5;
        cantidad_elementos[7] = 10;
        cantidad_elementos[8] = 32;
        cantidad_elementos[9] = 11;
        cantidad_elementos[10] = 1;
        cantidad_elementos[11] = 7;
        cantidad_elementos[12] = 4;
        cantidad_elementos[13] = 6;
        cantidad_elementos[14] = 6;


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
        String nombre_imagen = "c" + categoria + "s" + indice_palabra + version_panama;
        id_imagen = getResources().getIdentifier(nombre_imagen,"drawable",getPackageName());

        /**
         * Controla el caso en el que una palabra no tiene versión de Panama
         */
        if(id_imagen == 0 && version_panama.equals("p"))
        {
            showMessage("Esta palabra no cuenta con versión de panama");
            nombre_imagen = "c" + categoria + "s" + indice_palabra;
            id_imagen = getResources().getIdentifier(nombre_imagen,"drawable",getPackageName());
        }

        imageView.setImageResource(id_imagen);

    }

    /**
     * Actualiza el audio
     * Despliega un mensaje de error en caso de que el audio no exista, pero no tira la aplicación.
     * todo: Eliminar linea de codigo que cambia texto del boton, cuando las palabras esten listas.
     */
    public void setearAudio()
    {
        String nombre_audio = "c" + categoria + "s" + indice_palabra + genero + version_panama;
        id_audio = getResources().getIdentifier(nombre_audio,"raw",getPackageName());

        if(id_audio == 0)
            showMessage("Error: El audio " + nombre_audio + " no existe");

        //cambia el texto del boton
        //Button botonPalabra = (Button) findViewById(R.id.btn_palabra);
        //botonPalabra.setText("Palabra: " + indice_palabra);
    }

    public void setearTraduccion()
    {


        int id_array = getResources().getIdentifier("categoria"+categoria,"array",getPackageName());

        if(id_array != 0) {
            String[] array = getResources().getStringArray(id_array);

            if(array.length > (indice_palabra-1))
                traduccion = array[indice_palabra - 1];
            else
                traduccion = "No se encuentra disponible la traducción de esta palabra";
        }
        else
            traduccion = "No se encuentra disponible la traducción de esta categoria";
    }

    /**
     * Asigna la imagen y audio basandose en la palabra y categoría actual
     */
    public void setearRecursos()
    {
        setearCategoria();
        setearAudio();
        setearImagen();
        setearTraduccion();
    }

    /**
     * Cambia el genero de la voz
     * @param switchElement el switch cuyo cambio determina el genero de la voz del audio.
     */
    public void cambioGenero(View switchElement)
    {
        ImageView switch_image = (ImageView) switchElement;
        if(genero.equals("b")) {
            genero = "a";
            switch_image.setImageResource(R.drawable.switch1);
        }
        else {
            genero = "b";
            switch_image.setImageResource(R.drawable.switch2);
        }

        setearAudio();
    }

    /**
     * Alterna de versión de Costa Rica a Panama y viceversa
     * @param switchElement el switch cuyo cambio determina la versión
     */
    public void cambioVersion(View switchElement)
    {
        ImageView switch_image = (ImageView) switchElement;

        if(version_panama.equals("p")) {
            version_panama = "";
            switch_image.setImageResource(R.drawable.switch1);
        }
        else {
            version_panama = "p";
            switch_image.setImageResource(R.drawable.switch2);
        }

        setearAudio();
        setearImagen();
    }


    /**
     * Se encarga de hacer sonar el audio de la palabra.
     * Si el audio no existe, no realiza ninguna acción
     */
    public void sonarAudio()
    {
        if(id_audio != 0) {
            ReproductorAudio ra = new ReproductorAudio();
            ra.reproducirAudio(getApplicationContext(), id_audio);
        }
    }

    /**
     *
     * @param button el boton de retroceso
     */
    public void retroceder(View button)
    {
        //Solamente no retrocede en la palabra 1 de la categoria 1
        if(categoria > 1 || indice_palabra > 1)
        {
            indice_palabra--;

            //Si se intenta retroceder desde la primer palabra de una categoria
            if(indice_palabra == 0) { //
                categoria--;
                //Empieza desde el ultimo elemento de la categoria anterior.
                indice_palabra = cantidad_elementos[categoria-1];

            }

            setearRecursos();
        }
        else
        {
            showMessage("Esta es la primer palabra. No se puede retroceder.");
        }
    }

    /**
     * @param button El boton de avance
     */
    public void avanzar(View button)
    {
        //Avanza solo si no es la última palabra de la última categoria
        if(indice_palabra != cantidad_elementos[categoria-1] || categoria != cantidad_categorias ) {
            indice_palabra++;

            //Si avanza desde la ultima palabra de la categoria actual
            if (indice_palabra == cantidad_elementos[categoria - 1] + 1) {
                //Se posiciona en la primera palabra de la proxima categoria
                categoria++;
                indice_palabra = 1;
            }


            setearRecursos();
        }
        else
            showMessage("Esta es la última palabra del diccionario.");
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

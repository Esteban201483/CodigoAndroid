package com.example.sistema.Fraseodiccionario;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 *      Genero h = voz masculina
 *      Genero f = voz femenina
 *
 *      Versión de panama corresponde a la letra p. Si la tiene agregada, entonces
 *      dicho audio corresponde a un audio de Panama. Esto según el cambio indicado.
 *      Audios pendientes
 *
 * Ilustraciones:
 *      c[número de categoria]s[número de palabra]<versión de Pais>
 *      Versión de Panamá corresponde a la letra pn
 *      Versión de Costa Rica corresponde a la letra cr
*
 * Traducciones:
 *
 *  Importante: Los números de categoria del 1 al 9 NO empiezan con 0. Se empieza a contar
 *  a partir del uno
 */

public class casoLetra1 extends AppCompatActivity {

    private final String HOMBRE = "h";
    private final String MUJER = "m";
    private final String PANAMA = "pn";
    private final String COSTARICA = "cr";

    private final int cantidad_categorias = 15;

    private int indice_palabra = 0; //Indical cual es la palabra de la categoria actual.
    private int id_audio = 0; //Indica cual es el audio que debe sonar
    private int id_imagen = 0; //Indica cual es la imagen que se debe mostrar
    private int categoria = 0; //Indica la categoria actual
    private String genero = MUJER;

    private int cantidad_elementos[]; //Indica la cantidad de elemmentos por categoria
    private String categorias[]; //Almacena el nombre de las categorias

    private String traduccion = "";  //Almacena la traducción de la palabra actual

    private ImageView imageView;
    private ImageView imageViewPais;
    private ImageView imageViewGenero;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;

    private String version_pais = COSTARICA;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_letra1); //Asigna el layout a la actividad
        inicializarCategorias();

        imageView = (ImageView) findViewById(R.id.imageViewIlustracion);
        imageViewPais =(ImageView) findViewById(R.id.imageViewPanama);
        imageViewGenero =(ImageView) findViewById(R.id.imageViewGenero);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);

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
        indice_palabra = 0; //Accede a la primera palabra por default
        setearRecursos();
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

        cantidad_elementos[0] = 15;
        cantidad_elementos[1] = 3;
        cantidad_elementos[2] = 31;
        cantidad_elementos[3] = 9;
        cantidad_elementos[4] = 2;
        cantidad_elementos[5] = 9;
        cantidad_elementos[6] = 6;
        cantidad_elementos[7] = 11;
        cantidad_elementos[8] = 33;
        cantidad_elementos[9] = 12;
        cantidad_elementos[10] = 2;
        cantidad_elementos[11] = 8;
        cantidad_elementos[12] = 5;
        cantidad_elementos[13] = 7;
        cantidad_elementos[14] = 7;


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
        String nombre_imagen = "c" + categoria + "s" + indice_palabra + version_pais;

        if(indice_palabra == 0)  //Si es la sección
        {
            nombre_imagen = "c" + categoria + "s" + indice_palabra;
            //Oculta los switchs

            imageViewPais.setVisibility(View.INVISIBLE);
            imageViewGenero.setVisibility(View.INVISIBLE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);

        }
        else
        {
            imageViewPais.setVisibility(View.VISIBLE);
            imageViewGenero.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
        }

        id_imagen = getResources().getIdentifier(nombre_imagen,"drawable",getPackageName());

        /**
         * Controla el caso en el que una palabra no tiene versión de Panama
         */
        if(id_imagen == 0 && version_pais.equals(PANAMA))
        {
            showMessage("Esta palabra no cuenta con versión de panama");
            nombre_imagen = "c" + categoria + "s" + indice_palabra + COSTARICA;
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
        String nombre_audio = "c" + categoria + "s" + indice_palabra + genero + version_pais;
        id_audio = getResources().getIdentifier(nombre_audio,"raw",getPackageName());

        if(id_audio == 0)
            showMessage("Error: El audio " + nombre_audio + " no existe");
    }

    public void setearTraduccion()
    {
        if(indice_palabra != 0)  //No traduce las secciones
        {
            int id_array = getResources().getIdentifier("categoria" + categoria, "array", getPackageName());

            if (id_array != 0) {
                String[] array = getResources().getStringArray(id_array);

                if (array.length > (indice_palabra - 1))
                    traduccion = array[indice_palabra - 1];
                else
                    traduccion = "No se encuentra disponible la traducción de esta palabra";
            } else
                traduccion = "No se encuentra disponible la traducción de esta categoria";
        }
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
        if(genero.equals(HOMBRE)) {
            genero = MUJER;
            switch_image.setImageResource(R.drawable.switchm);
        }
        else {
            genero = HOMBRE;
            switch_image.setImageResource(R.drawable.switchh);
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

        if(version_pais.equals(PANAMA)) {
            version_pais = COSTARICA;
            switch_image.setImageResource(R.drawable.switchcr);
        }
        else {
            version_pais = PANAMA;
            switch_image.setImageResource(R.drawable.switchpn);
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
        if(categoria > 1 || indice_palabra > 0)
        {
            indice_palabra--;

            //Si se intenta retroceder desde la primer palabra de una categoria
            if(indice_palabra < 0) { //
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
                indice_palabra = 0; //La primer palabra sería la sección
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

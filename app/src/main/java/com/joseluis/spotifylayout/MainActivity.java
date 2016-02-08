package com.joseluis.spotifylayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.joseluis.spotifylayout.adapters.SptfAdapter;
import com.joseluis.spotifylayout.model.Song;
import com.joseluis.spotifylayout.utils.ImageUtils;
import com.joseluis.spotifylayout.views.SquareLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase tipo activity que ejecuta un layout similar al de la aplicación Spotify.
 *
 * Para hacer este tipo de vista he utilizado o me he apoyado en dos partes principalmente, primero si
 * ejecutamos la aplicación y hacemos scroll sobre la lista, vemos que el listado de canciones pasa por
 * encima de lo que sería el "HeaderView" pero pasando por dejabo de la vista toolbar. A simple vista parece
 * que es una unica vista pero son 3 partes, toolbar, headerview y listview.
 *
 * Para que la lista no tapase también el toolbar me he apoyado en la clase Bitmap y he recortado la imagen
 * en dos trozos, para luego unirlos de forma que no se note la diferencia.
 *
 * Por otra parte para poder ejecutar la acción del botón he utilizado la librería de soporte de Google.
 *
 * CoordinatorLayout (Vista), layout_anchorGravity y layout_anchor (Atributos) han sido fundamentales para
 * hacer una construcción mmás limpia.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Botón verde que se mueve con el scroll
     */
    private Button btnShuffle;

    /**
     * toolbar
     */
    private Toolbar toolbar;

    /**
     * Vista que contiene tanto el background como la imagen y texto del artista
     */
    private RelativeLayout rlBackground;

    /**
     * Vista de tipo lista
     */
    private ListView sptfListView;

    private int lastTopValueAssigned = 0;

    /**
     * Constante que identifica la posicion 1 de la lista, justo despues del header
     */
    private static final int FIRST_POS = 1;

    /**
     * Constante para crear el efecto blut en la imagen de fondo
     */
    private static final float BLUR_RADIUS = 25f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciamos las vistas
        initViews();

        //iniciamos los valores
        initValues();

        //iniciamos los listener
        initListener();
    }


    /**
     * Método que inicia las vistas
     */
    private void initViews(){

        //quitamos el status bar para poder poner la imagen hasta arriba
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        //instanciamos el toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //instanciamos el famoso boton verde
        btnShuffle = (Button) findViewById(R.id.btnShufle);

        //instanciamos la vista que almacenará el fondo
        rlBackground = (RelativeLayout) findViewById(R.id.rlBackground);

        //instanciamos la lista
        sptfListView = (ListView) findViewById(R.id.sptfListView);

        //inflamos
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SquareLayout headerLayout = (SquareLayout) inflater.inflate(R.layout.lay_header_sptf, null, false);

        //añadimos a la lista
        sptfListView.addHeaderView(headerLayout);
    }

    /**
     * Método que inicia los valores
     */
    private void initValues(){

        //instanciamos la lista
        List<Song> songs = new ArrayList<>();

        //añadimos 20 canciones a nuestra lista
        for(int i=0; i<20; i++){

            Song song = new Song();
            song.setIdSong(i);
            song.setAuthor(getString(R.string.author) + " " + i);
            song.setName(getString(R.string.song)  + " " + i);
            songs.add(song);
        }

        SptfAdapter adapter = new SptfAdapter(songs, this);
        sptfListView.setAdapter(adapter);
    }

    /**
     * Método que inicia los listener
     */
    private void initListener(){

        //instanciamos el listener OnGlobalLayout para obtener el tamaño de las vistas en px
        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);

        //instanciamos el listener para el scroll de la lista
        sptfListView.setOnScrollListener(scrollListener);
    }

    /**
     * Listener que controla la posicion del boton
     */
    private AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {}

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            //si la segunda vista aun es nula, nos salimos
            if(view.getChildAt(FIRST_POS) == null){
                return;
            }

            //seteamos inicialmente el valor de la segunda posicion de la lista (primera cancion) menos la mitad del alto del boton
            btnShuffle.setY(view.getChildAt(FIRST_POS).getTop() - (btnShuffle.getHeight() / 2));

            //si la posicion y + la mitad del boton es menor, es decir, nos hemos pasado porque el valor de la fila que incrementa la y no es unitaria, depende
            //de la fuerza con la que hagamos el gesto superase el máximo disponible, obligamos a que se quede fija
            if ((btnShuffle.getY() + (btnShuffle.getHeight() / 2) < toolbar.getHeight())) {
                btnShuffle.setY(toolbar.getHeight() - btnShuffle.getHeight() / 2);
            }
        }
    };

    /**
     * Listener que utilizamos para obtener las medidas de las vistas y poder crear las imagenes
     */
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

            //obtenemos el bitmap de recursos, recordad que la imagen debe ser lo suficientemente grande para abarcar el telefono
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);

            //creamos el efecto blur de la imagen
            ImageUtils.imageBlur(MainActivity.this, bmp, BLUR_RADIUS);

            //creamos la parte de la imagen que irá en el toolbar
            Bitmap bToolbar = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), toolbar.getHeight());
            toolbar.setBackgroundDrawable(new BitmapDrawable(bToolbar));

            //creamos la parte de la imagen que ira en el resto del layout
            Bitmap bBottomBar = Bitmap.createBitmap(bmp, 0, toolbar.getHeight(), bmp.getWidth(), bmp.getHeight() - toolbar.getHeight());
            rlBackground.setBackgroundDrawable(new BitmapDrawable(bBottomBar));

            //eliminamos el listener
            toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    };


}

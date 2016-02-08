package com.joseluis.spotifylayout.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.joseluis.spotifylayout.R;

/**
 * Clase que hereda de Listview y añade un headerview a la lista.
 */
public class SptfListView extends ListView {

    /**
     * Constructor
     * @param context - contexto de la app
     */
    public SptfListView(Context context) {
        super(context);
        initViews(context);
    }

    /**
     * Constructor
     * @param context - contexto de la app
     * @param attrs - atributos
     */
    public SptfListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    /**
     * Constructor
     * @param context - contexto de la app
     * @param attrs - atributos
     * @param defStyleAttr - estilos
     */
    public SptfListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    /**
     * Método que infla el header view y lo añade a la lista
     */
    private void initViews(Context context){

        //inflamos la vista
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SquareLayout headerLayout = (SquareLayout) inflater.inflate(R.layout.lay_header_sptf, this, false);

        //añadimos
        addHeaderView(headerLayout);
    }

}

# Developed By
José Luis Sánchez

# SpotifyLayout
En esta ocasión he querido recrear el Listview o listado de canciones de la popular aplicación Spotify. Siendo gran admirador de esta app, siempre tuve mucha inquietud sobre como llegar a implementar este layout. Para ello traigo un ejemplo y explicare como he llevado a cabo el desarrollo. El resultado, las imágenes finales.

Para llevar a cabo este desarrollo he implementado un Listview cuyo HeaderView es un RelativeLayout (SquareLayout) de forma cuadrada, mismo alto que ancho del dispositivo. De esta manera ya comenzamos teniendo un margen ente la primera canción y el toolbar o action bar de nuestra app.

A su vez, en la actividad, he creado otro SquareLayout de forma que encaje perfectamente en el hueco dejado por el HeaderView consiguiendo que al hacer scroll, las filas que hacen la función de canciones pasen por encima de la parte principal. 

Por otro lado, para conseguir el posicionamiento del famoso botón, he utilizado un CoordinatorLayout que viene incluida en la libreria de soporte.

```xml
    <Button
        app:layout_anchorGravity="bottom|center_horizontal"
        app:layout_anchor="@id/sqLayout"
        android:id="@+id/btnShufle"
        android:layout_width="@dimen/wButton"
        android:layout_height="@dimen/hButton"
        android:text="@string/aleatorio"
        android:textColor="@android:color/white"
        android:background="@drawable/dw_button_pink"
        />
```

En el XML anterior, observamos que hemos utilizado los atributos layout_anchorGravity y layout_anchor, con el primero posicionamos nuestro botón y con el segundo le decimos sobre que layout queremos que actue. 

Si volvemos otra vez la activiad (MainActivity.java), nos toca resolver el problema que supone que la lista pase por encima del HeaderView pero por debajo del ActionBar. Para ello se me ocurrio utilizar un bitmap cortado en 2 partes de forma que la parte superior sería el background del Toolbar y la parte inferior el background del headerView. De forma que al plasmarse no se nota que son 2 layouts diferentes.

Por último, para posicionar el boton en la parte top del Toolbar nos apoyamos en el ScrollListener de la Lista.

<pre><code>
 //seteamos inicialmente el valor de la segunda posicion de la lista (primera cancion) menos la mitad del alto del boton
 btnShuffle.setY(view.getChildAt(FIRST_POS).getTop() - (btnShuffle.getHeight() / 2));

//si la posicion y + la mitad del boton es menor, es decir, nos hemos pasado porque el valor de la fila que //incrementa la y no es unitaria, depende de la fuerza con la que hagamos el gesto superase el máximo disponible, obligamos a que se quede //fija
if ((btnShuffle.getY() + (btnShuffle.getHeight() / 2) < toolbar.getHeight())) {
    btnShuffle.setY(toolbar.getHeight() - btnShuffle.getHeight() / 2);
}
</pre></code>

![alt tag](https://github.com/jsancheh/SpotifyLayout/blob/master/captura1.png)

![alt tag](https://github.com/jsancheh/SpotifyLayout/blob/master/captura2.png)

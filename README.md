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


![alt tag](https://github.com/jsancheh/SpotifyLayout/blob/master/captura1.png)

![alt tag](https://github.com/jsancheh/SpotifyLayout/blob/master/captura2.png)

package com.joseluis.spotifylayout.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Clase que contiene utilidades para el manejo de imágenes.
 */
public class ImageUtils {

    public ImageUtils(){
        super();
    }

    /**
     * Método que crea un bitmap con efecto blur
     * @param context - contexto de la app
     * @param bmp - bitmap
     * @param radius - radio del efecto blut
     */
    public static void imageBlur(Context context, Bitmap bmp, float radius){

        final RenderScript rs = RenderScript.create(context);
        final Allocation input = Allocation.createFromBitmap( rs, bmp, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT );
        final Allocation output = Allocation.createTyped( rs, input.getType() );
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create( rs, Element.U8_4(rs) );
        script.setRadius(radius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bmp);

    }
}

package com.joseluis.spotifylayout.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;

import com.joseluis.spotifylayout.R;
import com.joseluis.spotifylayout.utils.ImageUtils;

/**
 * Desc:
 * Author: jsancheh
 * Version: 1.0
 */

public class SpotifyTest extends AndroidTestCase {

    public void testInitOK(){

        Bitmap bmp = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.bg);
        assertTrue(bmp != null);

        boolean valid = ImageUtils.imageBlur(getContext(), bmp, 10f);
        assertTrue(valid);
    }

    public void testInitKO(){

        Bitmap bmp = null;

        boolean valid = ImageUtils.imageBlur(getContext(), bmp, 10f);
        assertFalse(valid);
    }
}

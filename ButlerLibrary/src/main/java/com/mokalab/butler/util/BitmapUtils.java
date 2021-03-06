package com.mokalab.butler.util;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

/**
 * TODO: JAVA DOC
 *
 * Created by Pirdad S on 2014-08-19.
 */
public class BitmapUtils {

    private BitmapUtils() {}

    /**
     * Uses RenderScript to Blur a Bitmap. From support Library.
     * @param rs can use RenderScript.create(context). If blurring multiple times, pass the same instance of RenderScript.
     * @param bitmap Your original Bitmap.
     * @param blurRadius The radius which defines the amount to blur the image. Range is 0 - 25 inclusive.
     */
    @TargetApi(8)
    public static void renderScriptBlur(RenderScript rs, Bitmap bitmap, float blurRadius) {

        final Allocation input = Allocation.createFromBitmap(rs, bitmap); //use this constructor for best performance,
        // because it uses USAGE_SHARED mode which reuses memory
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(blurRadius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);
    }

    /**
     * Uses RenderScript to Blur a Bitmap.
     * @param rs can use RenderScript.create(context). If blurring multiple times, pass the same instance of RenderScript.
     * @param bitmap Your original Bitmap.
     * @param blurRadius The radius which defines the amount to blur the image. Range is 0 - 25 inclusive.
     */
    @TargetApi(17)
    public static void renderScriptBlur(android.renderscript.RenderScript rs, Bitmap bitmap, float blurRadius) {

        final android.renderscript.Allocation input = android.renderscript.Allocation.createFromBitmap(rs, bitmap); //use this constructor for best performance,
        // because it uses USAGE_SHARED mode which reuses memory
        final android.renderscript.Allocation output = android.renderscript.Allocation.createTyped(rs, input.getType());
        final android.renderscript.ScriptIntrinsicBlur script = android.renderscript.ScriptIntrinsicBlur.create(rs, android.renderscript.Element.U8_4(rs));
        script.setRadius(blurRadius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);
    }

    /**
     * This helper function can be used to convert your bitmap to any {@link android.graphics.Bitmap.Config}.[TYPE].
     * <br><br>
     * Example:
     * <br>
     * You can convert to Bitmap.Config.ARGB_8888 for {@link #renderScriptBlur(android.support.v8.renderscript.RenderScript,
     * android.graphics.Bitmap, float)} to work.
     *
     * @param bitmap your original Bitmap
     * @param bitmapType ex. Bitmap.Config.ARGB_8888
     * @return the converted Bitmap
     */
    public static Bitmap convertBitmapType(Bitmap bitmap, Bitmap.Config bitmapType) {

        int numPixels = bitmap.getWidth()* bitmap.getHeight();
        int[] pixels = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmapType);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());

        return result;
    }
}

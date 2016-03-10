package com.huxian.util;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * @author huxian99
 */
public class BlurUtil {

    private static final int DEFAULT_BLUR_RADIUS = 10;

    public static Bitmap apply(Context context, Bitmap sentBitmap) {
        return apply(context, sentBitmap, DEFAULT_BLUR_RADIUS);
    }

    public static Bitmap apply(Context context, Bitmap sentBitmap, int radius) {
        try {
            Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            //final RenderScript rs = RenderScript.create(context);
            //final Allocation input = Allocation.createFromBitmap(rs, sentBitmap);
            //final Allocation output = Allocation.createTyped(rs, input.getType());
            //final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            //script.setRadius(radius);
            //script.setInput(input);
            //script.forEach(output);
            //output.copyTo(bitmap);
            //
            //rs.destroy();
            //input.destroy();
            //output.destroy();
            //script.destroy();

            return bitmap;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sentBitmap;
    }

}

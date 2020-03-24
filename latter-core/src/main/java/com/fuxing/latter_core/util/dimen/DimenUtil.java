package com.fuxing.latter_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.fuxing.latter_core.app.Latte;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-04
 * Description:
 **/
public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
}

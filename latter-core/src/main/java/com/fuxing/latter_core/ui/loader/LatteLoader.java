package com.fuxing.latter_core.ui.loader;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.fuxing.latter_core.R;
import com.fuxing.latter_core.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-04
 * Description:
 **/
public class LatteLoader {


    private static final int LOADER_SICE_SCALE = 8;
    private static final int LOADER_OFFSET_SIZE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String DEFALUT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();


    public  static  void  showLoading(Context context,Enum<LoaderStyle> styleEnum){
        showLoading(context,styleEnum.name());
    }
    public  static  void  showLoading(Context context){
        showLoading(context,DEFALUT_LOADER);
    }



    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView view = LoaderCreator.create(type, context);

        dialog.setContentView(view);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams params = dialogWindow.getAttributes();
            params.width = deviceWidth / LOADER_SICE_SCALE;
            params.height = deviceHeight / LOADER_SICE_SCALE;
            params.height = params.height + deviceHeight / LOADER_OFFSET_SIZE;
            params.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();

    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}

package com.fuxing.latter_core.ui.camera;

import android.net.Uri;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-24
 * Description:存储中间值
 **/
public final class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
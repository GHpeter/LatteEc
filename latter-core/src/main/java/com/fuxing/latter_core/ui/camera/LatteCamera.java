package com.fuxing.latter_core.ui.camera;

import android.net.Uri;

import com.fuxing.latter_core.delegates.PermissionCheckerDelegate;
import com.fuxing.latter_core.util.file.FileUtil;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-24
 * Description:相机调用类
 **/
public class LatteCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}

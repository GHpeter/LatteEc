package com.fuxing.festec;

import android.app.Application;

import com.fuxing.latter_core.app.Latte;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:
 **/
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withAPiHost("http://127.0.0.1/").configure();
    }
}

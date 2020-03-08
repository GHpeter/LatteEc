package com.fuxing.latter_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:对外工具类 fragmentation
 **/
public final class Latte {

    /**
     * @param context:application context
     * @return
     */
    public static Configurator init(Context context) {
        getConfiguration().put(ConfigKeys.APPLCIATION_CONTEXT.name(),context.getApplicationContext());
        return  Configurator.getInstance();

    }

    public static WeakHashMap<Object, Object> getConfiguration() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static <T> T getConfiguration(Object key) {
        return Configurator.getInstance().getConfiguration(key);
    }

    public  static  Context getApplication(){
        return (Context) getConfiguration().get(ConfigKeys.APPLCIATION_CONTEXT.name());
    }
}

package com.fuxing.latter_core.app;

import java.util.WeakHashMap;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:配置类
 **/
public class Configurator {
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final void configure() {

        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withAPiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }


    private  void  checkConfiguration(){
        final  boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigType.API_HOST.name());
        if (!isReady){
            throw  new  RuntimeException("configuration is not ready ,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }


}

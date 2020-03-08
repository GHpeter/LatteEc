package com.fuxing.latter_core.net.rx;

import android.content.Context;

import com.fuxing.latter_core.net.RestClient;
import com.fuxing.latter_core.net.RestCreator;
import com.fuxing.latter_core.net.callback.IError;
import com.fuxing.latter_core.net.callback.IFailure;
import com.fuxing.latter_core.net.callback.IRequest;
import com.fuxing.latter_core.net.callback.ISuccess;
import com.fuxing.latter_core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:
 **/
public class RxRestClientBuilder {

    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;


    private RequestBody mBody = null;


    private Context mContext = null;
    private LoaderStyle mStyle = null;

    private File mFile;


    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody,
                mFile, mContext, mStyle);
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }


    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }


    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mStyle = style;
        return this;
    }

    /**
     * 默认style
     *
     * @param context
     * @return
     */
    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }


}

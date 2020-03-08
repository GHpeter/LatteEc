package com.fuxing.latter_core.net.interceptor;

import androidx.annotation.RawRes;

import com.fuxing.latter_core.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-08
 * Description:
 **/
public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder().code(200)
                .addHeader("Content-Type", "applciation/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("ok")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();

    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final  String json= FileUtil.getRawFile(rawId);
        return  getResponse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final  String url= chain.request().url().toString();
        if (url.contains(DEBUG_URL)){
            return  debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}

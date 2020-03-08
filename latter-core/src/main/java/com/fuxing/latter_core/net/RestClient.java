package com.fuxing.latter_core.net;

import android.content.Context;

import com.fuxing.latter_core.net.callback.IError;
import com.fuxing.latter_core.net.callback.IFailure;
import com.fuxing.latter_core.net.callback.IRequest;
import com.fuxing.latter_core.net.callback.ISuccess;
import com.fuxing.latter_core.net.callback.RequestCallBacks;
import com.fuxing.latter_core.net.download.DownLoadHandler;
import com.fuxing.latter_core.ui.LatteLoader;
import com.fuxing.latter_core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:请求的实现类
 **/
public class RestClient {


    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;

    //download
    private  String DOWNLOAD_DIR;
    private  String EXTENSION;
    private  String NAME;


    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;

    private LoaderStyle LOADER_STYLE;
    private Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,

                      LoaderStyle style
    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = style;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    private void request(HttpMethod method) {
        final RestService service =
                RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);

        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("post params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {

        if (BODY == null) {
            request(HttpMethod.PUT);

        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("put params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void download() {
        new DownLoadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME, SUCCESS, FAILURE, ERROR).handlerDownload();


    }
}

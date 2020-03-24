package com.fuxing.latter_core.net.rx;

import android.content.Context;

import com.fuxing.latter_core.net.HttpMethod;
import com.fuxing.latter_core.net.RestCreator;
import com.fuxing.latter_core.ui.loader.LatteLoader;
import com.fuxing.latter_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:请求的实现类
 **/
public class RxRestClient {


    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final RequestBody BODY;
    private final File FILE;

    private LoaderStyle LOADER_STYLE;
    private Context CONTEXT;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,

                        LoaderStyle style
    ) {
        this.URL = url;
        PARAMS.putAll(params);

        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = style;
    }


    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    public final Observable<String> get() {

        return request(HttpMethod.GET);
    }

    private Observable<String> request(HttpMethod method) {
//        final RestService service =
//                RestCreator.getRestService();
//
//

        final RxRestService service = RestCreator.getRxRestService();

        Observable<String> observable = null;


//        Call<String> call = null;
//        if (REQUEST != null) {
//            REQUEST.onRequestStart();
//        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
//                call = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
//                call = service.post(URL, PARAMS);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
//                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
//                call = service.delete(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
//                call = service.postRaw(URL, BODY);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
//                call = service.putRaw(URL, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);

//                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }

//        if (call != null) {
//            call.enqueue(getRequestCallback());
//        }

        return observable;
    }

//    private Callback<String> getRequestCallback() {
//        return new RequestCallBacks(
//                REQUEST,
//                SUCCESS,
//                FAILURE,
//                ERROR,
//                LOADER_STYLE
//        );
//    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);

        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("post params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }

    }

    public final Observable<String> put() {

        if (BODY == null) {
            return request(HttpMethod.PUT);

        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("put params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }


    public final Observable<ResponseBody> download() {

        final Observable<ResponseBody> responseBodyObservable =
                RestCreator.getRxRestService().download(URL, PARAMS);
        return responseBodyObservable;
//         new DownLoadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME, SUCCESS, FAILURE, ERROR).handlerDownload();


    }
}

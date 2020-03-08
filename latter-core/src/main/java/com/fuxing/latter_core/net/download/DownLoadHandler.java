package com.fuxing.latter_core.net.download;

import android.os.AsyncTask;

import com.fuxing.latter_core.net.RestCreator;
import com.fuxing.latter_core.net.callback.IError;
import com.fuxing.latter_core.net.callback.IFailure;
import com.fuxing.latter_core.net.callback.IRequest;
import com.fuxing.latter_core.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-08
 * Description:
 **/
public class DownLoadHandler {
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;

    //download
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownLoadHandler(String url,
                           IRequest request,
                           String download_dir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }


    public final void handlerDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    downLoadSuccess(response);
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure();
                }
            }
        });

    }

    /**
     * 成功后的处理
     *
     * @param response
     */
    private void downLoadSuccess(Response<ResponseBody> response) {
        final ResponseBody body = response.body();


        final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, body, NAME);

        //一定要判断一下，否则文件可能下载不全
        if (task.isCancelled()) {
            if (REQUEST != null) {
                REQUEST.onRequestEnd();
            }
        }
    }
}

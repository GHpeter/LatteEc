package com.fuxing.festec;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fuxing.latter_core.delegates.LatteDalegate;
import com.fuxing.latter_core.net.RestClient;
import com.fuxing.latter_core.net.callback.IError;
import com.fuxing.latter_core.net.callback.IFailure;
import com.fuxing.latter_core.net.callback.ISuccess;
import com.fuxing.latter_core.net.rx.RxRestClient;
import com.fuxing.latter_core.ui.LoaderStyle;

import java.util.Observable;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:
 **/
public class MainDelegate extends LatteDalegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        testRestClient();
    }

    private void testRestClient() {


        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext(), LoaderStyle.BallBeatIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSucess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }


}

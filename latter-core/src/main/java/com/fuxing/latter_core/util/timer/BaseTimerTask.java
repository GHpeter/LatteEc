package com.fuxing.latter_core.util.timer;

import java.util.TimerTask;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-24
 * Description:
 **/
public class BaseTimerTask extends TimerTask {
    private ITimerListener listener = null;

    public BaseTimerTask(ITimerListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        if (listener != null) {
            listener.onTimer();
        }
    }
}

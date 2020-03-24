package com.fuxing.latter_core.app;

import com.fuxing.latter_core.util.storage.LattePreference;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-24
 * Description:检查用户是否登录
 **/
public class AccountManager {
    public static void setSignTag(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * save user account, only by used login
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

    public static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    private enum SignTag {
        SIGN_TAG
    }
}

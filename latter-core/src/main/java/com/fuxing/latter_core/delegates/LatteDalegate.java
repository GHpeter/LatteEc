package com.fuxing.latter_core.delegates;

/**
 * @author ：Create by lichunfu
 * @Date : 2020-03-02
 * Description:
 **/
public    abstract class LatteDalegate  extends  PermissionCheckerDelegate{
    public  <T extends  LatteDalegate> T getParentDalegate(){
        return (T) getParentFragment();
    }


}

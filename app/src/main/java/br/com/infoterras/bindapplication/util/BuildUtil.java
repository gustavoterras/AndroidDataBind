package br.com.infoterras.bindapplication.util;

import br.com.infoterras.bindapplication.BuildConfig;

/**
 * Created by Gustavo on 23/01/2017.
 */

public class BuildUtil {

    public boolean isDebug(){
        return BuildConfig.DEBUG;
    }

    public String versionCode(){
        return BuildConfig.VERSION_NAME;
    }
}

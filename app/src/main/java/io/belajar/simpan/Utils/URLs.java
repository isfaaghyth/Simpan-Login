package io.belajar.simpan.Utils;

/**
 * Created by isfaaghyth on 17/1/17.
 */

public final class URLs {
    private static String URL_MAIN = "http://dev.daeng.id/testlogin/";
    private static String URL_LOGIN = "login.php";

    public static String getUrlLogin() {
        return URL_MAIN + URL_LOGIN;
    }
}

package io.belajar.simpan.Interfaces;


/**
 * Created by isfaaghyth on 17/1/17.
 */

public interface NetworkListener {
    void onResponse(String response);
    String username();
    String password();
}

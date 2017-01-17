package io.belajar.simpan.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by isfaaghyth on 17/1/17.
 */

public class LoginModel {
    @SerializedName("status")
    private String status;

    @SerializedName("login")
    private Login login;

    public String getStatus() {
        return status;
    }

    public Login getLogin() {
        return login;
    }

    public class Login {
        @SerializedName("username")
        private String username;

        @SerializedName("token")
        private String token;

        public String getUsername() {
            return username;
        }

        public String getToken() {
            return token;
        }
    }
}

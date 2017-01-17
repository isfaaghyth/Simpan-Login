package io.belajar.simpan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.belajar.simpan.Interfaces.NetworkListener;
import io.belajar.simpan.Model.LoginModel;
import io.belajar.simpan.Presenter.NetworkPresenter;
import io.belajar.simpan.Utils.URLs;

public class LoginActivity extends AppCompatActivity implements NetworkListener {

    @BindView(R.id.edt_username)
    EditText edtUsername;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    private NetworkPresenter requestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        Hawk.init(this).build();

        checkIsLogin();
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        requestData = new NetworkPresenter(this, this);
        if (!this.username().isEmpty() && !this.password().isEmpty()) {
            requestData.sendRequest(URLs.getUrlLogin());
        } else {
            showToast("Field username dan password tidak boleh kosong");
        }
    }

    @Override
    public void onResponse(String response) {
        Gson gson = new GsonBuilder().create();
        LoginModel loginItem = gson.fromJson(response, LoginModel.class);
        if (loginItem.getStatus().equals("success")) {

            Hawk.put("login", true);
            Hawk.put("username", loginItem.getLogin().getUsername());
            Hawk.put("token", loginItem.getLogin().getToken());

            openMain();
        } else {
            showToast("Terjadi Kesalahan. Periksa kembali username dan password anda");
        }
    }

    private void checkIsLogin() {
        boolean isLogin = Hawk.contains("login");
        if (isLogin) {
            isLogin = Hawk.get("login");
            if (isLogin) openMain();
        }
    }

    private void showToast(String value) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    private void openMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public String username() {
        return edtUsername.getText().toString();
    }

    @Override
    public String password() {
        return edtPassword.getText().toString();
    }
}

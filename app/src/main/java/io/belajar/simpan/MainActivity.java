package io.belajar.simpan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_username)
    TextView txtUsername;

    @BindView(R.id.txt_token)
    TextView txtToken;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Hawk.init(this).build();

        initializeData();
    }

    private void initializeData() {
        txtUsername.setText("Hai, " + Hawk.get("username") + " welcome!");
        txtToken.setText("Your token: " + Hawk.get("token") + " (secret)");
    }

    @OnClick(R.id.btn_logout)
    public void onClickLogout() {
        Hawk.deleteAll();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}

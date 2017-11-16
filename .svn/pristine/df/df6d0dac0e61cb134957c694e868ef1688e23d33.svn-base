package com.example.nappy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nappy.R;
import com.example.nappy.app.AppClient;
import com.example.nappy.inter.NetworkOnResult;
import com.example.nappy.network.NetRequest;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by liufangya on 2017/10/11.
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private ImageView mIvSetting;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private CheckBox mCbPassword;
    private CheckBox mCbAutoLogin;
    private Button mBtnLogin;
    private Button mBtnZhece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAutoLogin();
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initView();
        initEvent();
        isCheck();
    }

    private void initEvent() {
        mCbAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mCbPassword.setChecked(true);
                }
            }
        });
    }

    private void initView() {
        mIvSetting = (ImageView) findViewById(R.id.iv_setting);
        mIvSetting.setOnClickListener(this);
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtUsername.setOnClickListener(this);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtPassword.setOnClickListener(this);
        mCbPassword = (CheckBox) findViewById(R.id.cb_password);
        mCbPassword.setOnClickListener(this);
        mCbAutoLogin = (CheckBox) findViewById(R.id.cb_auto_login);
        mCbAutoLogin.setOnClickListener(this);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnZhece = (Button) findViewById(R.id.btn_zhece);
        mBtnZhece.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_zhece:
                break;
        }
    }

    private void login() {

        final String username = mEtUsername.getText().toString().trim();
        final String password = mEtPassword.getText().toString().trim();
        //进行登录校验
        if (username.length() > 10) {
            Toast.makeText(this, "用户名长度小于10", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() > 10) {
            Toast.makeText(this, "密码长度小于10", Toast.LENGTH_SHORT).show();
            return;
        }
        final JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("username", username);
            jsonObj.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new NetRequest("login.in")
                .setJsonBody(jsonObj)
                .setNetworkOnResult(new NetworkOnResult() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.i(TAG, "onSuccess: " + jsonObject);
                        try {
                            String request = jsonObject.getString("response");
                            if ("成功".equals(request)) {
                                String identity=jsonObject.getString("result");
                                AppClient.setString("identity",identity);
                                AppClient.setString("username",username);
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                //记住密码不一定自动登录，自动登录一定记住密码
                                if (mCbPassword.isChecked()) {
                                    AppClient.setString("username", username);
                                    AppClient.setString("password", password);
                                    AppClient.setBoolean("isPassword", true);
                                }
                                if (mCbAutoLogin.isChecked()) {
                                    Log.i(TAG, "onSuccess: AutoLogin");
                                    AppClient.setString("username", username);
                                    AppClient.setString("password", password);
                                    AppClient.setBoolean("isPassword", true);
                                    AppClient.setBoolean("isAutoLogin", true);
                                }
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                LoginActivity.this.finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError() {

                    }


                });
    }


    /**
     * 如果设置过记住密码，则还原界面
     */
    public void isCheck() {
        if (AppClient.getBoolean("isPassword")) {
            mCbPassword.setChecked(true);
            mEtUsername.setText(AppClient.getString("username"));
            mEtPassword.setText(AppClient.getString("password"));
        }
    }

    /**
     * 用于自动登录的方法
     */
    public void isAutoLogin() {
        if (AppClient.getBoolean("isAutoLogin")) {
            final String username = AppClient.getString("username");
            String password = AppClient.getString("password");
            final JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("username", username);
                jsonObj.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new NetRequest("login.in")
                    .setJsonBody(jsonObj)
                    .setNetworkOnResult(new NetworkOnResult() {
                        @Override
                        public void onSuccess(JSONObject jsonObject) {
                            Log.i(TAG, "onSuccess: " + jsonObject);
                            try {
                                String request = jsonObject.getString("response");
                                if ("成功".equals(request)) {
                                    String identity=jsonObject.getString("result");
                                    //保存权限和身份，便于后期使用
                                    AppClient.setString("identity",identity);
                                    AppClient.setString("username",username);

                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    LoginActivity.this.finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError() {

                        }
                    });
        }


    }

}

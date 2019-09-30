package com.sopho.chat.module.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.sopho.chat.R;
import com.sopho.chat.base.BaseActivity;
import com.sopho.chat.bean.User;
import com.sopho.chat.module.contract.LoginContract;
import com.sopho.chat.module.model.LoginModel;
import com.sopho.chat.module.presenter.LoginPresenter;
import com.sopho.chat.util.IntentUtils;
import com.sopho.chat.util.PermissionUtil;
import com.sopho.chat.util.SPUtils;
import com.sopho.chat.xmpp.XmppApiHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jivesoftware.smack.util.StringUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @BindView(R.id.userName)
    TextInputEditText userName;
    @BindView(R.id.passWord)
    TextInputEditText passWord;
    @BindView(R.id.login)
    Button login;


    @Inject
    XmppApiHelper xmppApiHelper;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initComponent() {
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {

            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {

            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {

            }
        }, new RxPermissions(this));
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        new Thread(() -> xmppApiHelper.connect()).start();
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        if (StringUtils.isNotEmpty(userName.getText().toString()) && StringUtils.isNotEmpty(passWord.getText().toString())) {
            boolean result = xmppApiHelper.login(userName.getText().toString(), passWord.getText().toString());//先登录openfire服务器
            if (result) {
                mPresenter.login(userName.getText().toString(), passWord.getText().toString());//自己的服务器
            } else {
                showToast("openfire服务器登录失败");
            }
        } else {
            showToast("用户名或密码不能为空");
        }
    }

    @Override
    public void returnFail(String message) {
        showToast(message);
    }

    @Override
    public void returnLogin(User result) {
        showToast("登录成功");
        SPUtils.getInstance().putObject("user", result);
        IntentUtils.goMainActivity(this);
        finish();
    }

}

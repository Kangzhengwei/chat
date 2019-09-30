package com.sopho.chat.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.sopho.chat.di.component.ActivityComponent;
import com.sopho.chat.di.component.DaggerActivityComponent;
import com.sopho.chat.di.moudle.ActivityModule;
import com.sopho.chat.util.ActivityManagerUtils;
import com.sopho.chat.util.TUtil;
import com.sopho.chat.util.ToastUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity 基类
 *
 * @param <T>
 * @param <E>
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {
    @Inject
    protected E mModel;
    public T mPresenter;
    public Context mContext;
    private Unbinder bind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(getContentView());
        bind = ButterKnife.bind(this);
        ActivityManagerUtils.getActivityManager().addActivity(this);
        this.initInject();
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresenter();
        this.initComponent();
        this.initView(savedInstanceState);
        this.initData();
    }

    /**
     * 初始化布局
     *
     * @return
     */
    abstract protected int getContentView();

    /**
     * dagger注入
     */
    abstract protected void initInject();

    /**
     * presenter初始化
     */
    abstract protected void initPresenter();

    /**
     * 初始化组件
     */
    abstract protected void initComponent();

    /**
     * 初始化view
     *
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    abstract public void initData();

    public void initToolbar(Toolbar mToolbar, String title) {
        this.setSupportActionBar(mToolbar);
        ActionBar bar = this.getSupportActionBar();
        bar.setTitle(title);
        bar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getInstance().getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        ActivityManagerUtils.getActivityManager().finishActivity(this);
        bind.unbind();

    }

    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }
}

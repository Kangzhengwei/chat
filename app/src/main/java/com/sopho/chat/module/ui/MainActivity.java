package com.sopho.chat.module.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sopho.chat.R;
import com.sopho.chat.adapter.ViewPagerAdapter;
import com.sopho.chat.base.BaseActivity;
import com.sopho.chat.bean.TabEntity;
import com.sopho.chat.constant.Constant;
import com.sopho.chat.xmpp.XmppReceiver;
import com.sopho.chat.xmpp.XmppService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.commonTabLayout)
    CommonTabLayout commonTabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private XmppReceiver receiver;
    private String[] mTitles = {"消息", "联系人", "我的"};


    private int[] mIconUnselectIds = {R.mipmap.tab_move_icon,
            R.mipmap.tab_comprehensive_icon,
            R.mipmap.tab_me_icon};

    private int[] mIconSelectIds = {R.mipmap.tab_move_pressed_icon,
            R.mipmap.tab_comprehensive_pressed_icon,
            R.mipmap.tab_me_pressed_icon};

    private List<Fragment> fragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPagerAdapter adapter;


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initComponent() {
        receiver = new XmppReceiver();
        registerReceiver(receiver, new IntentFilter(Constant.ACTION));
        startService(new Intent(this, XmppService.class));
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        fragments.add(MessageFragment.newInstance("", ""));
        fragments.add(FriendFragment.newInstance("", ""));
        fragments.add(MyFragment.newInstance("", ""));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), mTitles, fragments);
        viewPager.setAdapter(adapter);
        commonTabLayout.setTabData(mTabEntities);

        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                commonTabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        stopService(new Intent(this, XmppService.class));
    }


}

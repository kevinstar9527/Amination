package com.example.admin.amination;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.example.admin.amination.widget.CustomLlistView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/8.
 */

public class AlphaAnimationActivity extends FragmentActivity{

    private CustomLlistView lv;
    private TextView tvTop;
    private float alpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_animation);
        lv = (CustomLlistView) findViewById(R.id.listview);
        CommonAdapter<String> commonAdapter = new CommonAdapter<String>(this,R.layout.item_listview) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                    helper.setImageResource(R.id.iv,R.mipmap.ic_launcher);
            }
        };
        commonAdapter.addAll(getDatas());
        lv.setAdapter(commonAdapter);
        initAnimaltion();
    }

    /**
     * 动画相关逻辑
     */
    private void initAnimaltion() {
        tvTop = (TextView) findViewById(R.id.tv_alpha_view_top);
        lv.setCustomOnScrollChangedListener(new CustomLlistView.CustomOnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                Log.e("ListView",l+"");
            }
        });
        alpha = 1;
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                      //  alpha = alpha  - 0.1f;
                     //   tvTop.setAlpha(alpha);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view != null) {
                    if (view.getChildAt(0) == null ) {
                        return;
                    } else {
                        float scrollY = getScrollY(view);
                        float alpha = getAlpha(scrollY,view);
                        tvTop.setAlpha(alpha);
                    }

                }

            }
        });

    }

    /**
     * 获取ListView 中滑动的距离
     * @return
     */
    private float getScrollY(AbsListView view){
        float scrollY;

        View c = view.getChildAt(0);
        if (c == null ) {
            return 0;
        }
        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();
        int height = c.getHeight();
        if (firstVisiblePosition > 0) {
            scrollY = height;
        } else {
            scrollY = -top;
        }
        return scrollY;
    }

    /**
     * 根据滑动Y值计算alpha值
     * @return
     */
    private float getAlpha(float scrollY,AbsListView view){
        float alphaValue = 0;
        if (view.getChildAt(0).getHeight()!=0) {
             alphaValue = scrollY/view.getChildAt(0).getHeight();
        }
        return alphaValue;
    }
    /**
     * 假数据
     * @return
     */
    private List<String> getDatas(){
        List<String> testStrings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            testStrings.add(new String());
        }
        return testStrings;
    }
}

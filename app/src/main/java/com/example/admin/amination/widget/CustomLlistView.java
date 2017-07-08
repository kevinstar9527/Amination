package com.example.admin.amination.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by admin on 2017/7/8.
 */

public class CustomLlistView extends ListView {

    public interface CustomOnScrollChangedListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);

    }

    private CustomOnScrollChangedListener customOnScrollChangedListener;

    public CustomOnScrollChangedListener getCustomOnScrollChangedListener() {
        return customOnScrollChangedListener;
    }

    public void setCustomOnScrollChangedListener(CustomOnScrollChangedListener customOnScrollChangedListener) {
        this.customOnScrollChangedListener = customOnScrollChangedListener;
    }

    public CustomLlistView(Context context) {
        super(context);
    }

    public CustomLlistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (customOnScrollChangedListener != null) {
            customOnScrollChangedListener.onScrollChanged(l,t,oldl,oldt);
        }
    }
}

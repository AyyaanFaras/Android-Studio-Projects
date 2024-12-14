package com.example.finance;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
    private boolean isScrollingEnabled = true;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isScrollingEnabled) {
            return false; // Prevent scrolling
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setScrollingEnabled(boolean enabled) {
        isScrollingEnabled = enabled;
    }
}

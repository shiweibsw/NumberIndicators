package com.knigit.davion.numberindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiwei on 2017/5/27.
 */

public class NumberIndicator extends RelativeLayout {
    private ViewPager mViewpager;
    private int mLastPosition = -1;
    private int mIndicatorWidth = -1;
    private int mIndicatorHeight = -1;
    private int mIndicatorTextSize = -1;
    private int mIndicatorTextColor = -1;
    private TextView first;
    private TextView second;

    public NumberIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public NumberIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NumberIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberIndicator);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.NumberIndicator_ni_width, -1);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.NumberIndicator_ni_height, -1);
        mIndicatorTextSize = typedArray.getInt(R.styleable.NumberIndicator_ni_textsize, 25);
        mIndicatorTextColor = typedArray.getResourceId(R.styleable.NumberIndicator_ni_textcolor, Color.WHITE);
        initChild();
        adjustViewPosition();
    }

    private void initChild() {
        first = new TextView(getContext());
        second = new TextView(getContext());
        first.setTextSize(mIndicatorTextSize);
        second.setTextSize(mIndicatorTextSize);
        first.setTextColor(getResources().getColor(mIndicatorTextColor));
        second.setTextColor(getResources().getColor(mIndicatorTextColor));
    }

    private void adjustViewPosition() {
        first.setGravity(Gravity.CENTER);
        second.setGravity(Gravity.CENTER);
        setGravity(Gravity.CENTER);
    }

    public void setViewPager(ViewPager viewPager) {
        mViewpager = viewPager;
        if (mViewpager != null && mViewpager.getAdapter() != null) {
            createIndicators();
        }
    }

    private List<Integer> indicatorImages = new ArrayList<>();

    public void setIndicatorWithImage(List<Integer> resId) {
        indicatorImages.addAll(resId);
    }

    private void createIndicators() {
        removeAllViews();
        int count = mViewpager.getAdapter().getCount();
        if (count <= 0) {
            return;
        }
        if (!indicatorImages.isEmpty()) {
            if (count != indicatorImages.size()) {
                return;
            }
        }
        int currentItem = mViewpager.getCurrentItem();
        mLastPosition = currentItem;
        if (indicatorImages.isEmpty())
            first.setText(String.valueOf(mLastPosition));
        else
            first.setBackgroundResource(indicatorImages.get(mLastPosition));
        addView(second, mIndicatorWidth, mIndicatorHeight);
        addView(first, mIndicatorWidth, mIndicatorHeight);
        mViewpager.addOnPageChangeListener(mInternalPageChangeListener);

    }

    private final ViewPager.OnPageChangeListener mInternalPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (mViewpager.getAdapter() == null || mViewpager.getAdapter().getCount() <= 0) {
                return;
            }
            if (mLastPosition > position) {
                backward(mLastPosition);
            } else {
                forward(mLastPosition);
            }
            mLastPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private void forward(final int position) {
        if (!indicatorImages.isEmpty()) {
            first.setBackgroundResource(indicatorImages.get(position));
        } else {
            first.setText(String.valueOf(position));
        }
        first.clearAnimation();
        Animation mForwardOut = AnimationUtils.loadAnimation(getContext(), R.anim.down_out);
        mForwardOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (!indicatorImages.isEmpty()) {
                    second.setBackgroundResource(indicatorImages.get(position + 1));
                } else {
                    second.setText(String.valueOf(position + 1));
                }
                second.clearAnimation();
                Animation mForwardIn = AnimationUtils.loadAnimation(getContext(), R.anim.down_in);
                mForwardIn.setStartOffset(200);
                second.setAnimation(mForwardIn);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        first.setAnimation(mForwardOut);
    }

    private void backward(final int position) {
        if (!indicatorImages.isEmpty()) {
            second.setBackgroundResource(indicatorImages.get(position));
        } else {
            second.setText(String.valueOf(position));
        }
        second.clearAnimation();
        Animation mBackwardOut = AnimationUtils.loadAnimation(getContext(), R.anim.up_out);
        mBackwardOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (!indicatorImages.isEmpty()) {
                    first.setBackgroundResource(indicatorImages.get(position - 1));
                } else {
                    first.setText(String.valueOf(position - 1));
                }
                first.clearAnimation();
                Animation mBackwardIn = AnimationUtils.loadAnimation(getContext(), R.anim.up_in);
                mBackwardIn.setStartOffset(200);
                first.setAnimation(mBackwardIn);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        second.setAnimation(mBackwardOut);
    }
}

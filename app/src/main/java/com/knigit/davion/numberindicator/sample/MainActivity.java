package com.knigit.davion.numberindicator.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.knigit.davion.numberindicator.NumberIndicator;

import java.util.ArrayList;
import java.util.List;

import static com.knigit.davion.numberindicator.sample.R.id.indicator1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ViewPager mViewPager1;
    private NumberIndicator mIndicator1;

    private ViewPager mViewPager2;
    private NumberIndicator mIndicator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        mViewPager1.setAdapter(new SamplePagerAdapter());
        mIndicator1 = (NumberIndicator) findViewById(indicator1);
        mIndicator1.setViewPager(mViewPager1);

        List<Integer> indicators = new ArrayList<>();
        indicators.add(R.drawable.index_1);
        indicators.add(R.drawable.index_2);
        indicators.add(R.drawable.index_3);
        indicators.add(R.drawable.index_4);
        indicators.add(R.drawable.index_5);

        mViewPager2 = (ViewPager) findViewById(R.id.viewpager2);
        mViewPager2.setAdapter(new SamplePagerAdapter());
        mIndicator2 = (NumberIndicator) findViewById(R.id.indicator2);
        mIndicator2.setIndicatorWithImage(indicators);
        mIndicator2.setViewPager(mViewPager2);


    }


}

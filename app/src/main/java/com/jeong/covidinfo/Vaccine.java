package com.jeong.covidinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import me.relex.circleindicator.CircleIndicator3;

public class Vaccine extends AppCompatActivity {
    //코로나에 대한 전반적인 정보를 뷰페이저로 1.백신정보(종류별 특징, 부작용) 2.거리두기
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    private int page_num = 3;
    private CircleIndicator3 Indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        viewPager2 = findViewById(R.id.viewpager);
        pagerAdapter = new MypagerAdapter(this, page_num);
        viewPager2.setAdapter(pagerAdapter);

        Indicator = findViewById(R.id.indicator);
        Indicator.setViewPager(viewPager2);
        Indicator.createIndicators(page_num,0);

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    viewPager2.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
               // Indicator.animatePageSelected(position%page_num);
                Indicator.animatePageSelected(position);
            }
        });
    }
}
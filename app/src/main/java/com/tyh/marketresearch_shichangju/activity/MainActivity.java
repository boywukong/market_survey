package com.tyh.marketresearch_shichangju.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.fragment.FirstFragment;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout m_tabLayout = this.findViewById(R.id.main_activity_tablayout);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);

        /*m_tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(m_tabLayout, 20, 20);
            }

            public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
                Class<?> tabLayout = tabs.getClass();
                Field tabStrip = null;
                try {
                    tabStrip = tabLayout.getDeclaredField("mTabStrip");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                tabStrip.setAccessible(true);
                LinearLayout llTab = null;
                try {
                    llTab = (LinearLayout) tabStrip.get(tabs);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
                int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

                for (int i = 0; i < llTab.getChildCount(); i++) {
                    View child = llTab.getChildAt(i);
                    child.setPadding(0, 0, 0, 0);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                    params.leftMargin = left;
                    params.rightMargin = right;
                    child.setLayoutParams(params);
                    child.invalidate();
                }
            }
        });*/

        m_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                                 @Override
                                                 public void onTabSelected(TabLayout.Tab tab) {

                                                     //Log.d("TabItem getPosition():" , " "+tab.getPosition());
                                                     int position = tab.getPosition();
                                                     //m_viewPager2.setCurrentItem(position, false);
                                                     setCurrentFragment(position);
                                                 }

                                                 //fragmen跳转
                                                 private void setCurrentFragment(int position) {
                                                     if (position == 0) {
                                                         Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.firstFragment);
                                                     } else {
                                                         Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.secondFragment);
                                                     }
                                                 }

                                                 @Override
                                                 public void onTabUnselected(TabLayout.Tab tab) {

                                                 }

                                                 @Override
                                                 public void onTabReselected(TabLayout.Tab tab) {

                                                 }
                                             }
        );
    }
}
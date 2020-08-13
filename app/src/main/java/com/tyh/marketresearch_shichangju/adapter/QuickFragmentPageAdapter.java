package com.tyh.marketresearch_shichangju.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class QuickFragmentPageAdapter <T extends Fragment> extends FragmentPagerAdapter {
    private List<T> mList;
    private String[] mStrings;

    /**
     * @param fm
     * @param list
     * @param titles PageTitles
     */
    public QuickFragmentPageAdapter(FragmentManager fm, List<T> list, String[] titles) {
        super(fm);
        mList = list;
        mStrings = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("---into Fragment---",position+"");
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings == null ? super.getPageTitle(position) : mStrings[position];
    }

    /*
    *
    * 不做任何操作的的destroyItem，目的为了不destroy数据，不知道效果怎么样？？？
    * */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("---Quick**Adapter---"," ------ Excute destroyItem(); none has done. ------ ");
        return;
    }
}

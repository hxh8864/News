package cracker.example.com.news.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cracker on 2017/5/16.
 */

public class NewsPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private ArrayList<String> mTitleList;


    public NewsPageAdapter(FragmentManager fm, List<Fragment> fragments, ArrayList<String> titleList) {
        super(fm);
        this.fragments = fragments;
        this.mTitleList = titleList;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if(fragments == null){
            return 0;
        }
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mTitleList != null && position <mTitleList.size()){
            return mTitleList.get(position);
        }
       return "noTitle";
    }
}

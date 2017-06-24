package cracker.example.com.news.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cracker.example.com.news.Adapter.NewsPageAdapter;
import cracker.example.com.news.Fragment.EntertainmentFragment;
import cracker.example.com.news.Fragment.HotsFragment;
import cracker.example.com.news.Fragment.SportFragment;
import cracker.example.com.news.Fragment.TechnologyFragment;
import cracker.example.com.news.R;


public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NewsPageAdapter mPageAdapter;
    private List<Fragment> fragments;
    private ArrayList<String> mTitleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initFragments();
        initTitleList();

        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        mPageAdapter  = new NewsPageAdapter(getSupportFragmentManager(),
                fragments,mTitleList);
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }



    private void initTitleList() {
        mTitleList.add("头条");
        mTitleList.add("体育");
        mTitleList.add("科技");
        mTitleList.add("娱乐");
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HotsFragment());
        fragments.add(new SportFragment());
        fragments.add(new TechnologyFragment());
        fragments.add(new EntertainmentFragment());
    }
}

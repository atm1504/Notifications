package com.grobo.notifications.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.grobo.notifications.R;
import com.grobo.notifications.adapters.TimetableRecyclerAdapter;

public class TimetableActivity extends AppCompatActivity {

    private TimetableFragmentAdapter timetableFragmentAdapter;

    private ViewPager mViewPager;

    private static String mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        timetableFragmentAdapter = new TimetableFragmentAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.tt_container);
        mViewPager.setAdapter(timetableFragmentAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tt_tab_layout);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timetable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class DayFragment extends Fragment {

        private TimetableRecyclerAdapter ttRecyclerAdapter;
        private RecyclerView ttRecyclerView;

        public DayFragment() {
        }

        public static DayFragment newInstance(int page){
            Bundle args = new Bundle();
            args.putInt("dayNumber", page);
            DayFragment fragment = new DayFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            switch (getArguments().getInt("dayNumber")){

                case 1:
                    mDay = "monday";
                    break;
                case 2:
                    mDay = "tuesday";
                    break;
                case 3:
                    mDay = "wednesday";
                    break;
                case 4:
                    mDay = "thursday";
                    break;
                case 5:
                    mDay = "friday";
                    break;
                default:

            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_timetable, container, false);

            ttRecyclerView = (RecyclerView) rootView.findViewById(R.id.tt_recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            ttRecyclerView.setLayoutManager(layoutManager);

            ttRecyclerAdapter = new TimetableRecyclerAdapter();
            ttRecyclerView.setAdapter(ttRecyclerAdapter);

            return rootView;
        }
    }


    public class TimetableFragmentAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 5;
        private String tabTitles[] = new String[] { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};

        public TimetableFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return DayFragment.newInstance(position + 1);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}

package com.grobo.notifications.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
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

import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grobo.notifications.R;
import com.grobo.notifications.adapters.TimetableRecyclerAdapter;
import com.grobo.notifications.models.TimetableItem;

import java.util.ArrayList;
import java.util.List;

public class TimetableActivity extends AppCompatActivity {

    private TimetableFragmentAdapter timetableFragmentAdapter;

    private ViewPager mViewPager;

    private static String mDay;
    private static List<TimetableItem> timetableItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(TimetableActivity.this, R.color.statusColor));

        timetableFragmentAdapter = new TimetableFragmentAdapter(getSupportFragmentManager());
        timetableItemList = new ArrayList<TimetableItem>();

        mViewPager = (ViewPager) findViewById(R.id.tt_container);
        mViewPager.setAdapter(timetableFragmentAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tt_tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

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

        private FirebaseDatabase mFirebaseDatabase;
        private DatabaseReference mTimetableDatabaseReference;
        private ChildEventListener mChildEventListener;

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

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mTimetableDatabaseReference = mFirebaseDatabase.getReference().child("1801").child("ee");
            mTimetableDatabaseReference.keepSynced(true);


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
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            ttRecyclerView.setLayoutManager(layoutManager);
            ttRecyclerView.setHasFixedSize(true);

            mTimetableDatabaseReference.child(mDay).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    timetableItemList.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        TimetableItem timetableItem = dataSnapshot1.getValue(TimetableItem.class);
                        timetableItemList.add(timetableItem);
                    }
                    ttRecyclerAdapter = new TimetableRecyclerAdapter(getContext(), timetableItemList);
                    ttRecyclerView.setAdapter(ttRecyclerAdapter);
                    ttRecyclerAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            return rootView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            timetableItemList.clear();
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

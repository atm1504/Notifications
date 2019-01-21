package com.grobo.notifications.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.grobo.notifications.R;
import com.grobo.notifications.fragments.AttendenceFragment;
import com.grobo.notifications.fragments.CalenderFragment;
import com.grobo.notifications.fragments.ExamFragment;
import com.grobo.notifications.fragments.FeedFragment;
import com.grobo.notifications.fragments.HomeFragment;
import com.grobo.notifications.fragments.LinksFragment;
import com.grobo.notifications.fragments.MessFragment;
import com.grobo.notifications.fragments.NotificationsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static FirebaseDatabase mfirebase;
    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createNotificationChannel();
        getDatabase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        openHome();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_timetable:
                openTimetable();
                break;
            case R.id.nav_calender:
                openCalender();
                break;
            case R.id.nav_notifications:
                openNotifications();
                break;
            case R.id.nav_home:
                openHome();
                break;
            case R.id.nav_explore:
                openExplore();
                break;
            case R.id.nav_mess:
                openMess();
                break;
            case R.id.nav_exam:
//                openExam();
                Toast.makeText(this, "Yaar, exam to aane de!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_links:
                openLinks();
                break;
            case R.id.nav_feed:
                openFeed();
                break;
            default:
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void openCalender(){
        CalenderFragment calenderFragment = new CalenderFragment();
        updateFragment(calenderFragment, 1);
    }
    private void openTimetable(){
        startActivity(new Intent(MainActivity.this, TimetableActivity.class));
    }
    private void openNotifications(){
        NotificationsFragment notificationsFragment = new NotificationsFragment();
        updateFragment(notificationsFragment, 1);
    }
    private void openHome(){
//        NavUtils.navigateUpFromSameTask(this);
        HomeFragment homeFragment = new HomeFragment();
        updateFragment(homeFragment, 0);
    }
    private void openExplore(){
        AttendenceFragment attendenceFragment = new AttendenceFragment();
        updateFragment(attendenceFragment, 1);
    }
    private void openMess(){
        MessFragment messFragment = new MessFragment();
        updateFragment(messFragment, 1);
    }
    private void openExam(){
        ExamFragment examFragment = new ExamFragment();
        updateFragment(examFragment, 1);
    }
    private void openLinks(){
        LinksFragment linksFragment = new LinksFragment();
        updateFragment(linksFragment, 1);
    }
    private void openFeed(){
        FeedFragment feedFragment = new FeedFragment();
        updateFragment(feedFragment, 1);
    }

    private void getDatabase() {
        if (FirebaseApp.getApps(this).size() == 0) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            FirebaseDatabase.getInstance().setPersistenceCacheSizeBytes(25000000);
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FCM channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(getString(R.string.default_notification_channel_id), name, importance);
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);

            channel.enableVibration(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void updateFragment(Fragment fragment, int bStack) {
        Log.d(TAG, "updateFragment: " + fragment.toString());
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout_main, fragment, fragment.getTag());

        if (bStack ==1) {
            transaction.addToBackStack(fragment.getTag());
        } else if (bStack == 0){
            manager.popBackStack();
            transaction.disallowAddToBackStack();
        }
        transaction.commit();
    }
}

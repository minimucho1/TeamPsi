package com.psi.utascheduleplanner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

//MainActivity consists of the tabs which will create create a sched, manage schedule, etc.
public class MainActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener {

    private ViewPager tabsviewPager;
    private ActionBar mActionBar;
    private Tabsadapter mTabsAdapter;
    private String curUser;
    private String curPassword;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_signout) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentInfo = getIntent();

        String userName = intentInfo.getStringExtra("username");
        String userPassword = intentInfo.getStringExtra("password");

        curUser = userName;
        curPassword = userPassword;

        tabsviewPager = (ViewPager) findViewById(R.id.tabspager);

        mTabsAdapter = new Tabsadapter(getSupportFragmentManager());

        tabsviewPager.setAdapter(mTabsAdapter);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab createtab = getSupportActionBar().newTab().setText("Create A Schedule").setTabListener(this);
        Tab managetab = getSupportActionBar().newTab().setText("Manage Schedule").setTabListener(this);
        Tab usersettingtab = getSupportActionBar().newTab().setText("User Settings").setTabListener(this);
        Tab timerestricttab = getSupportActionBar().newTab().setText("Time Restriction").setTabListener(this);

        getSupportActionBar().addTab(createtab);
        getSupportActionBar().addTab(managetab);
        getSupportActionBar().addTab(usersettingtab);
        getSupportActionBar().addTab(timerestricttab);

        //This helps in providing swiping effect for v7 compat library
        tabsviewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    public String getCurUser() {
        return curUser;
    }

    public String getCurPassword() {
        return curPassword;
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

    @Override
    public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(Tab selectedtab, FragmentTransaction arg1) {
        // TODO Auto-generated method stub
        tabsviewPager.setCurrentItem(selectedtab.getPosition()); //update tab position on tap
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }
}

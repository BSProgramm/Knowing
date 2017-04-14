package ru.badsprogramm.knowing;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private static long back_pressed;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mToolbar.setSubtitleTextColor(Color.WHITE);

        ViewPagerInit();
        NavDrawerInit();
        setFragment(adapter);
    }


    public void ViewPagerInit(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Algebra(), "Алгебра");

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabTextColors(Color.LTGRAY, Color.WHITE);
        tabLayout.setSelectedTabIndicatorHeight(3);
        tabLayout.setVisibility(GONE);
    }

    public void NavDrawerInit(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.drawer1);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        adapter.clear();

        switch (id){
            case R.id.drawer1:
                mToolbar.setTitle(R.string.algebra);
                adapter.addFragment(new Algebra(), "Алгебра");
                adapter.addFragment(new Algebra(), "Алгебра");
                adapter.addFragment(new Algebra(), "Алгебра");
                setFragment(adapter);
                break;
            case R.id.drawer2:
                mToolbar.setTitle(R.string.geometric);
                adapter.addFragment(new Geometric(), "Геометрия");
                setFragment(adapter);
                break;
            case R.id.drawer3:
                Uri address = Uri.parse("http://vk.com/badsprogramm");
                Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openlink);
                break;
            case R.id.drawer4:
                address = Uri.parse("http://vk.com/badsprogramm");
                openlink = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openlink);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back_pressed + 2000 > System.currentTimeMillis())
                super.onBackPressed();

            else

                Toast.makeText(getBaseContext(), getResources().getString(R.string.exit),
                        Toast.LENGTH_SHORT).show();

            back_pressed = System.currentTimeMillis();
        }
    }

    public void setFragment(ViewPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
        if (adapter.getCount() == 1) tabLayout.setVisibility(GONE);
        else{
            tabLayout.setVisibility(VISIBLE);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        void clear(){
            mFragmentList.clear();
            mFragmentTitleList.clear();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}

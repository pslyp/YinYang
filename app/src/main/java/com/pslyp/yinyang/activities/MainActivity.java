package com.pslyp.yinyang.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pslyp.yinyang.R;
import com.pslyp.yinyang.fragments.GraphFragment;
import com.pslyp.yinyang.fragments.HomeFragment;
import com.pslyp.yinyang.fragments.MeFragment;
import com.pslyp.yinyang.fragments.SearchFragment;
import com.pslyp.yinyang.services.SharedPreferenceManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActionBar toolbar;
    private BottomNavigationView navView;

    private Timer _timer = new Timer();
    private TimerTask timer;

//    private CountDownTimer timer;
    private byte click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount() != 0)
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        navView.setSelectedItemId(R.id.nav_home);

        Toast.makeText(this, "กด 'กลับ' อีกครั้งเพื่อออก", Toast.LENGTH_SHORT).show();

        click++;
        if(click == 2)
            finish();

        timer = new TimerTask() {
            @Override
            public void run() {
                click = 0;
                timer.cancel();
            }
        };

        _timer.schedule(timer, 1000);

//        click++;
//        timer = new CountDownTimer(2000, 1) {
//            @Override
//            public void onTick(long l) {
//                Log.e("Timer", Long.toString(l));
//
//                if(click == 2)
//                    finish();
//            }
//
//            @Override
//            public void onFinish() {
//                Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();
//
//                click = 0;
//                timer.cancel();
//            }
//        }.start();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.nav_home :
                getSupportActionBar().setTitle("Home");
                loadFragment(new HomeFragment());
                return true;
            case R.id.nav_search :
                getSupportActionBar().setTitle("Search");
                loadFragment(new SearchFragment());
                return true;
            case R.id.nav_graph :
                getSupportActionBar().setTitle("Graph");
                loadFragment(new GraphFragment());
                return true;
            case R.id.nav_me :
                getSupportActionBar().setTitle("Me");
                loadFragment(new MeFragment());
                return true;
        }
        return false;
    }

    private void initInstance() {
//        toolbar = getSupportActionBar();

        SharedPreferenceManager spm = new SharedPreferenceManager.Builder(this)
                .name("Login")
                .mode(MODE_PRIVATE)
                .build();

        if(!spm.getBoolean("SIGNIN", false)) {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        }

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

//        toolbar.setTitle("Home");
        loadFirstFragment();
    }

    private void loadFirstFragment() {
        getSupportActionBar().setTitle("Home");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, new HomeFragment());
        transaction.commit();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

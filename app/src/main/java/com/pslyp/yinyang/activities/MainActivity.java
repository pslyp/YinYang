package com.pslyp.yinyang.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
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

    private HomeFragment homeFragment = new HomeFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private GraphFragment graphFragment = new GraphFragment();
    private MeFragment meFragment = new MeFragment();

    private BottomNavigationView navView;

    private TextView toolbarText;

    private Timer _timer = new Timer();
    private TimerTask timer;

    private byte click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    @Override
    public void onBackPressed() {
        int selectItemId = navView.getSelectedItemId();

        if(R.id.nav_home != selectItemId) {
            loadFragment(homeFragment);
            navView.setSelectedItemId(R.id.nav_home);
        }

        if(R.id.nav_home == selectItemId) {
            Toast.makeText(this, "กด 'กลับ' อีกครั้งเพื่อออก", Toast.LENGTH_SHORT).show();

            click++;
            if (click == 2)
//                finish();
                super.onBackPressed();

            timer = new TimerTask() {
                @Override
                public void run() {
                    click = 0;
                    timer.cancel();
                }
            };

            _timer.schedule(timer, 1000);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.nav_home :
                toolbarText.setText("Home");
                loadFragment(homeFragment);
                return true;
            case R.id.nav_search :
                toolbarText.setText("Search");
                loadFragment(searchFragment);
                return true;
            case R.id.nav_graph :
                toolbarText.setText("Graph");
                loadFragment(graphFragment);
                return true;
            case R.id.nav_me :
                toolbarText.setText("Me");
                loadFragment(meFragment);
                return true;
        }
        return false;
    }

    private void initInstance() {
        SharedPreferenceManager spm = new SharedPreferenceManager.Builder(this)
                .name("Login")
                .mode(MODE_PRIVATE)
                .build();

        if(!spm.getBoolean("SIGNIN", false)) {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        }

        toolbarText = findViewById(R.id.text_toolbar);

//        Toolbar toolbar = findViewById(R.id.app_bar);
//        toolbar.

        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

//        toolbar.setTitle("Home");
//        loadFirstFragment();
        loadFragment(homeFragment);
        toolbarText.setText("Home");
    }

    private void loadFragment(Fragment fragment) {
        if(fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}

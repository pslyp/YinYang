package com.pslyp.yinyang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.Menu;
import com.pslyp.yinyang.services.SharedPreferenceManager;
import com.pslyp.yinyang.services.adapter.MenuAdapter;
import com.pslyp.yinyang.services.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView menuListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferenceManager spm = new SharedPreferenceManager.Builder(this)
                .name("Login")
                .mode(MODE_PRIVATE)
                .build();

        if(!spm.getBoolean("SIGNIN", false)) {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        }
    }

    private void initInstance() {

        menuListView = findViewById(R.id.menu_list_view);

        Call<List<Menu>> call = RetrofitClient.getInstance().api().getMenu();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                List<Menu> menuList = response.body();

                MenuAdapter adapter = new MenuAdapter(getApplicationContext(), R.layout.listview_row, menuList);

                menuListView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.e("Menu", t.getMessage());
            }
        });

    }
}

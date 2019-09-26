package com.pslyp.yinyang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.Menu;
import com.pslyp.yinyang.services.api.RetrofitClient;

import retrofit2.Call;

public class MenuDetailsActivity extends AppCompatActivity {

    private TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);

        initInstance();
        initData();
    }

    private void initInstance() {
        nameTextView = findViewById(R.id.name_text_view);
    }

    private void initData() {
        Menu detail = (Menu) getIntent().getSerializableExtra("DETAIL");

        nameTextView.setText(detail.getName());
    }
}

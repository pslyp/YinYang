package com.pslyp.yinyang.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.pslyp.yinyang.R;
import com.pslyp.yinyang.adapter.ViewPagerAdapter;
import com.pslyp.yinyang.fragments.HowToFragment;
import com.pslyp.yinyang.fragments.IngredientFragment;
import com.pslyp.yinyang.models.Menu;
import com.pslyp.yinyang.services.api.RetrofitClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class MenuDetailsActivity extends AppCompatActivity {

    private TextView nameText, ingredientText, howtoText;
    private ImageView menuImage;

    private String url = "http://pilot.cp.su.ac.th/usr/u07580536/yhinyhang/images/menu/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);

        initInstance();
        getData();
    }

    private void initInstance() {
        nameText = findViewById(R.id.name_text_view);
        ingredientText = findViewById(R.id.ingredient_text_view);
        howtoText = findViewById(R.id.howto_text_view);
        menuImage = findViewById(R.id.pic_image_view);

        //Tab Layout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new IngredientFragment(), "วัตถุดิบ");
        viewPagerAdapter.addFragment(new HowToFragment(), "วิธีทำ");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        //Tab Layout
    }

    private void getData() {
        Menu detail = getIntent().getParcelableExtra("DETAIL");

//        Log.e("Get details", detail.getName());

//        nameText.setText(detail.getName());
//        ingredientText.setText(detail.getIngredient());
//        howtoText.setText(detail.getHowto());
        Picasso.get().load(url.concat(detail.getImage())).into(menuImage);
    }
}

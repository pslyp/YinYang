package com.pslyp.yinyang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pslyp.yinyang.R;
import com.pslyp.yinyang.services.SharedPreferenceManager;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences sp;
//    private SharedPreferences.Editor editor;
//    final private String PREF_NAME = "LOGIN";

    private SharedPreferenceManager spm;

    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initInstanse();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.sign_in_button :
                signIn();
                break;
            default: break;
        }
    }

    private void initInstanse() {
        spm = new SharedPreferenceManager.Builder(this)
                .name("Login")
                .mode(MODE_PRIVATE)
                .build();

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
    }

    private void signIn() {
        spm.edit();
        spm.putBoolean("SIGNIN", true);
        spm.commit();

//        editor.putBoolean("SIGNIN", true);
//        editor.commit();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}

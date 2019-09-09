package com.pslyp.yinyang.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.Response;
import com.pslyp.yinyang.services.SharedPreferenceManager;
import com.pslyp.yinyang.services.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferenceManager spm;

    private Button signInButton, signUpButton;
    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initInstanse();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.sign_in_button :
                signIn();
                break;
            case R.id.sign_up_button :
                signUp();
                break;
        }
    }

    private void initInstanse() {
        spm = new SharedPreferenceManager.Builder(this)
                .name("Login")
                .mode(MODE_PRIVATE)
                .build();

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
        signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(this);
    }

    private void signIn() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        Call<Response> call = RetrofitClient.getInstance().api().signIn(email, password);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response res = response.body();

                if(res.getStatus() == 200) {
                    if(res.getMessage().equals("success")) {
                        spm.edit();
                        spm.putBoolean("SIGNIN", true);
                        spm.commit();

                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("Sign in", t.getMessage());
            }
        });
    }

    private void signUp() {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
        finish();
    }

}

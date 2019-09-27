package com.pslyp.yinyang.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.User;
import com.pslyp.yinyang.services.SharedPreferenceManager;
import com.pslyp.yinyang.services.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    private SharedPreferenceManager spm;

    private TextView nameText, emailText;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_me, container, false);

        initInstance(view);
        initLogic(view);

        return view;
    }

    private void initInstance(View view) {
        spm = new SharedPreferenceManager.Builder(getContext())
                .name("Login")
                .mode(getContext().MODE_PRIVATE)
                .build();

        nameText = view.findViewById(R.id.name_text_view);
        emailText = view.findViewById(R.id.email_text_view);
    }

    private void initLogic(View view) {
        Call<User> call = RetrofitClient.getInstance().api().getProfile(spm.getString("EMAIL", ""));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                nameText.setText(user.getUsername());
                emailText.setText(user.getEmail());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Get user", t.getMessage());
            }
        });
    }

}

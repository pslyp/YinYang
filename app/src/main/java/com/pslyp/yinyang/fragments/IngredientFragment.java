package com.pslyp.yinyang.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.User;
import com.pslyp.yinyang.services.api.RetrofitClient;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientFragment extends Fragment {

    private TextView howToText;

    public IngredientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);

        initInstance(view);
        initLogic(view);

        return view;
    }

    private void initInstance(View view) {
        howToText = view.findViewById(R.id.howto_text_view);
    }

    private void initLogic(View view) {
//        Call<User> call = RetrofitClient.getInstance().api().get
        howToText.setText("disjgoijsdgojeowkgpekpojewohew;lgjewpojcpeowfpoewjfpoejojfeofdisjgoijsdgojeowkgpekpojewohew;lgjewpojcpeowfpoewjfpoejojfeof" +
                "disjgoijsdgojeowkgpekpojewohew;lgjewpojcpeowfpoewjfpoejojfeof" +
                "disjgoijsdgojeowkgpekpojewohew;lgjewpojcpeowfpoewjfpoejojfeof" +
                "disjgoijsdgojeowkgpekpojewohew;lgjewpojcpeowfpoewjfpoejojfeof" +
                "disjgoijsdgojeowkgpekpojewohew;lgjewpojcpeowfpoewjfpoejojfeof" +
                "disjgoijsdgojeowkgpekpojewohew;lgjewpojcpeowfpoewjfpoejojfeof");
    }

}

package com.pslyp.yinyang.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pslyp.yinyang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HowToFragment extends Fragment {


    public HowToFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_how_to, container, false);
    }

}

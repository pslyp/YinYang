package com.pslyp.yinyang.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.pslyp.yinyang.R;
import com.pslyp.yinyang.activities.MenuDetailsActivity;
import com.pslyp.yinyang.adapter.MenuRecyclerAdapter;
import com.pslyp.yinyang.models.Menu;
import com.pslyp.yinyang.services.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private EditText editText;

    private MenuRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    private List<Menu> mMenuList;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initInstance(view);

        return view;
    }

    private void initInstance(View view) {

        recyclerView = view.findViewById(R.id.search_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        editText = view.findViewById(R.id.search_edit_text);
        editText.setText(" ");


        Call<List<Menu>> call = RetrofitClient.getInstance().api().getMenu();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                mMenuList = response.body();

                adapter = new MenuRecyclerAdapter(getContext(), mMenuList);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new MenuRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Menu item) {
                        Intent intent = new Intent(getContext(), MenuDetailsActivity.class);
                        intent.putExtra("DETAIL", item);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.e("Get menu", t.getMessage());
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Menu> filteredList = new ArrayList<>();

        for(Menu item : mMenuList) {
            if(item.getName().contains(text)) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

}

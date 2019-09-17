package com.pslyp.yinyang.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pslyp.yinyang.R;
import com.pslyp.yinyang.activities.MenuDetailsActivity;
import com.pslyp.yinyang.adapter.MenuAdapter;
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
public class HomeFragment extends Fragment {

//    private MenuAdapter adapter;

    private ActionBar actionBar;
    private Toolbar toolbar;

    private ListView menuListView;
    private List<Menu> mCurrentData = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initInstance(view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(android.view.Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initInstance(View view) {
        toolbar = view.findViewById(R.id.app_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

//        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
////        actionBar.setDisplayShowTitleEnabled(true);
////        actionBar.setTitle("Home");
//
//        toolbar = view.findViewById(R.id.app_bar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
////        toolbar.setTitle("Home");

        menuListView = view.findViewById(R.id.menu_list_view);

        Call<List<Menu>> call = RetrofitClient.getInstance().api().getMenu();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                List<Menu> menuList = response.body();

                mCurrentData = menuList;

//                setData(menuList);

                MenuAdapter adapter = new MenuAdapter(getContext(), R.layout.listview_row, mCurrentData);
                menuListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.e("Menu", t.getMessage());
            }
        });

//        MenuAdapter adapter = new MenuAdapter(getContext(), R.layout.listview_row, mCurrentData);
//        menuListView.setAdapter(adapter);

//        menuListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
////                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                int lastItemInScree = firstVisibleItem + visibleItemCount;
//
//
//
////                adapter.notifyDataSetChanged();
//            }
//        });

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Menu menu =

                Intent intent = new Intent(getContext(), MenuDetailsActivity.class);
                intent.putExtra("position", String.valueOf(i));
                startActivity(intent);

                Toast.makeText(getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(List<Menu> menuList) {
        for(int i=0; i<=menuList.size(); i++) {
            mCurrentData = menuList;
        }
    }

}

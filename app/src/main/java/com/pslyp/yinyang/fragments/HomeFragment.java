package com.pslyp.yinyang.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.pslyp.yinyang.R;
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

    private MenuAdapter adapter;

    private ListView menuListView;
    private List<Menu> mCurrentData = new ArrayList<Menu>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initInstance(view);

        return view;
    }

    private void initInstance(View view) {
        menuListView = view.findViewById(R.id.menu_list_view);

        Call<List<Menu>> call = RetrofitClient.getInstance().api().getMenu();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                List<Menu> menuList = response.body();

//                mCurrentData = menuList;

//                setData(menuList);

                adapter = new MenuAdapter(getContext(), R.layout.listview_row, menuList);
                menuListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.e("Menu", t.getMessage());
            }
        });

//        MenuAdapter adapter = new MenuAdapter(getContext(), R.layout.listview_row, mCurrentData);
//        menuListView.setAdapter(adapter);

        menuListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setData(List<Menu> menuList) {
        for(int i=0; i<=menuList.size(); i++) {
            mCurrentData = menuList;
        }
    }

}

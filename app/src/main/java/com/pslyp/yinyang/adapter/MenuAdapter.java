package com.pslyp.yinyang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.Menu;
import com.pslyp.yinyang.models.Response;
import com.pslyp.yinyang.services.api.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MenuAdapter extends ArrayAdapter<Menu> {

    private Context mContext;
    private List<Menu> mMenuList;
    private int mLayoutResId;

    //    private String url = "http://pilot.cp.su.ac.th/usr/u07580570/YinYang/images/profiles/";
    private String url = "http://pilot.cp.su.ac.th/usr/u07580536/yhinyhang/images/menu/";

    public MenuAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Menu> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mMenuList = objects;
        this.mLayoutResId = resource;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(mLayoutResId, null);

        final Menu menu = mMenuList.get(position);

        TextView name = view.findViewById(R.id.name_text_view);
        TextView category = view.findViewById(R.id.category_text_view);
        TextView yin = view.findViewById(R.id.yin_text_view);
        TextView yang = view.findViewById(R.id.yang_text_view);
        CircularImageView image = view.findViewById(R.id.pic_circle_image);
//        final ToggleButton favorite = view.findViewById(R.id.favorite_toggle_button);
//        setFavoriteButton(favorite, menu.getFavorite());
//
//        favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if(isChecked) {
//                    favorite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
//
//                    updateFavorite(view, menu.getId(), "1");
//
//                    Toast.makeText(mContext, menu.getName() + "Favorite", Toast.LENGTH_SHORT).show();
//                } else {
//                    favorite.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
//
//                    updateFavorite(view, menu.getId(), "0");
//
//                    Toast.makeText(mContext, menu.getName() + "None Favorite", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



        name.setText(menu.getName());
        category.setText(menu.getCategory());
        yin.setText(menu.getYhin());
        yang.setText(menu.getYhang());
        Picasso.get().load(url.concat(menu.getImage())).into(image);

        return view;
    }

    private void setFavoriteButton(ToggleButton favoriteToggle, String favorite) {
        if(favorite.equals("1")) {
            favoriteToggle.setChecked(true);
            favoriteToggle.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        }
        else {
            favoriteToggle.setChecked(false);
            favoriteToggle.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

    private void updateFavorite(final View view, String id, Integer favorite) {
        Call<Response> call = RetrofitClient.getInstance().api().updateFavorite(id, favorite);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response res = response.body();

                if(res.getStatus() == 200) {
                    if(res.getMessage().equals("success")) {
                        Toast.makeText(view.getContext(), "Update favorite success", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("Update Favorite", t.getMessage());
            }
        });
    }

}

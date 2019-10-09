package com.pslyp.yinyang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.Menu;
import com.pslyp.yinyang.models.Response;
import com.pslyp.yinyang.services.SharedPreferenceManager;
import com.pslyp.yinyang.services.api.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Menu> mMenuList;
    private SharedPreferenceManager manager;

    private String url = "http://pilot.cp.su.ac.th/usr/u07580536/yhinyhang/images/menu/";

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(Menu item);
    }

    public MenuRecyclerAdapter(Context mContext, List<Menu> mMenuList) {
        this.mContext = mContext;
        this.mMenuList = mMenuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_row_home, parent, false);

        manager = new SharedPreferenceManager.Builder(mContext)
                .name("Favorite")
                .mode(mContext.MODE_PRIVATE)
                .build();

        manager.edit();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu menu = mMenuList.get(position);

        holder.menu = menu;
        holder.nameText.setText(menu.getName());
        holder.categoryText.setText(menu.getCategory());
        holder.yinText.setText(menu.getYhin());
        holder.yangText.setText(menu.getYhang());
        Picasso.get().load(url.concat(menu.getImage())).into(holder.imageMenu);

        holder.setFavoriteToggle();
        holder.bind(menu, mListener);
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    public void filterList(ArrayList arrayList) {
        this.mMenuList = arrayList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameText, categoryText, yinText, yangText;
        public CircularImageView imageMenu;
        public ToggleButton favoriteToggle;

        public Menu menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.name_text_view);
            categoryText = itemView.findViewById(R.id.category_text_view);
            yinText = itemView.findViewById(R.id.yin_text_view);
            yangText = itemView.findViewById(R.id.yang_text_view);
            imageMenu = itemView.findViewById(R.id.pic_circle_image);
            favoriteToggle = itemView.findViewById(R.id.favorite_toggle_button);
        }

        public void bind(final Menu item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }

        public void setFavoriteToggle() {
            if (menu.getFavorite() == 0) {
                favoriteToggle.setChecked(false);
                favoriteToggle.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);

//            manager.putInt(menu.getId(), 0);
            } else {
                favoriteToggle.setChecked(true);
                favoriteToggle.setBackgroundResource(R.drawable.ic_favorite_black_24dp);

//            manager.putInt(menu.getId(), 1);
            }

            favoriteToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        favoriteToggle.setChecked(true);
                        favoriteToggle.setBackgroundResource(R.drawable.ic_favorite_black_24dp);

                        manager.putInt(menu.getId(), 1);
                        manager.commit();

                        menu.setFavorite(1);

                        updateFavorite(1);

                        Log.e("Toggle", String.valueOf(favoriteToggle.isChecked()));
                    } else {
                        favoriteToggle.setChecked(false);
                        favoriteToggle.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);

                        manager.putInt(menu.getId(), 0);
                        manager.commit();

                        menu.setFavorite(0);

                        updateFavorite(0);

                        Log.e("Toggle", String.valueOf(favoriteToggle.isChecked()));
                    }
                }
            });
        }

        private void updateFavorite(final Integer favorite) {
            Call<Response> call = RetrofitClient.getInstance().api().updateFavorite(menu.getId(), favorite);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Response res = response.body();

                    if(res.getStatus() == 200) {
                        if(res.getMessage().equals("success")) {
//                            if(favorite == 0)
//                                Toast.makeText(mContext, "เลิกถูกใจ", Toast.LENGTH_SHORT).show();
//                            else
//                                Toast.makeText(mContext, "ถูกใจ", Toast.LENGTH_SHORT).show();
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

}

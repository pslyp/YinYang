package com.pslyp.yinyang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.Menu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Menu> mMenuList;

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu menu = mMenuList.get(position);

        holder.nameText.setText(menu.getName());
        holder.categoryText.setText(menu.getCategory());
        holder.yinText.setText(menu.getYhin());
        holder.yangText.setText(menu.getYhang());
        Picasso.get().load(url.concat(menu.getImage())).into(holder.imageMenu);

        setFavoriteToggle(holder.favoriteToggle, menu.getFavorite());

        holder.bind(menu, mListener);
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameText, categoryText, yinText, yangText;
        public CircularImageView imageMenu;
        public ToggleButton favoriteToggle;

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
    }

    private void setFavoriteToggle(final ToggleButton toggle, int favorite) {
        if(favorite == 0) {
            toggle.setChecked(false);
            toggle.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        } else {
            toggle.setChecked(true);
            toggle.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        }

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    toggle.setBackgroundResource(R.drawable.ic_favorite_black_24dp);

                    Log.e("Toggle", String.valueOf(toggle.isChecked()));
                } else {
                    toggle.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);

                    Log.e("Toggle", String.valueOf(toggle.isChecked()));
                }
            }
        });
    }
}

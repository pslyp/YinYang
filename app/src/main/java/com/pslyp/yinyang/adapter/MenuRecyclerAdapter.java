package com.pslyp.yinyang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameText, categoryText, yinText, yangText;
        public CircularImageView imageMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.name_text_view);
            categoryText = itemView.findViewById(R.id.category_text_view);
            yinText = itemView.findViewById(R.id.yin_text_view);
            yangText = itemView.findViewById(R.id.yang_text_view);
            imageMenu = itemView.findViewById(R.id.pic_circle_image);
        }
    }
}

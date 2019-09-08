package com.pslyp.yinyang.services.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pslyp.yinyang.R;
import com.pslyp.yinyang.models.Menu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends ArrayAdapter<Menu> {

    private Context mContext;
    private List<Menu> mMenuList;
    private int mLayoutResId;

//    private String url = "http://pilot.cp.su.ac.th/usr/u07580570/YinYang/images/profiles/";
    private String url = "http://pilot.cp.su.ac.th/usr/u07580536/yhinyhang/images/";

    public MenuAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Menu> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mMenuList = objects;
        this.mLayoutResId = resource;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mLayoutResId, null);

        Menu menu = mMenuList.get(position);

        TextView name = view.findViewById(R.id.name_text_view);
        TextView category = view.findViewById(R.id.category_text_view);
        TextView yin = view.findViewById(R.id.yin_text_view);
        TextView yang = view.findViewById(R.id.yang_text_view);
        ImageView image = view.findViewById(R.id.pic_circle_image);

        name.setText(menu.getName());
        category.setText(menu.getCategory());
        yin.setText(menu.getYhin());
        yang.setText(menu.getYhang());
        Picasso.get().load(url.concat(menu.getImage())).into(image);

        return view;
    }
}

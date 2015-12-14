package com.huxian.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxian.R;
import com.huxian.model.Menu;

import java.util.List;

/**
 * @author huxian99
 */
public class DrawerAdapter extends ArrayAdapter<Menu> {

    private LayoutInflater inflater;

    public DrawerAdapter(Context context, List<Menu> menuList) {
        super(context, -1, menuList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_drawer_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_drawer_item);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_drawer_item);
        imageView.setImageResource(getItem(position).getIcon());
        textView.setText(getItem(position).getText());
        convertView.setBackgroundColor(Color.TRANSPARENT);
        return convertView;
    }
}

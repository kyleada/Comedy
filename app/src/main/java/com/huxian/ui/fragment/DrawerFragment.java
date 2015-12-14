package com.huxian.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.huxian.R;
import com.huxian.model.Menu;
import com.huxian.adapter.DrawerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huxian99
 */
public class DrawerFragment extends ListFragment {

    private DrawerAdapter adapter;

    private List<Menu> menuList;

    public static DrawerFragment newInstance() {
        return new DrawerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMenuList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new DrawerAdapter(getActivity(), menuList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (onItemClickListener != null) {
            onItemClickListener.itemClick(position, ((Menu) getListAdapter().getItem(position)).getText());
        }
    }

    private void initMenuList() {
        Menu menuItem;
        menuList = new ArrayList<>();
        menuItem = new Menu(getResources().getString(R.string.in_theaters_chinese), R.mipmap.ic_launcher);
        menuList.add(menuItem);
        menuItem = new Menu(getResources().getString(R.string.coming_soon_chinese), R.mipmap.ic_launcher);
        menuList.add(menuItem);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, String text);
    }

}

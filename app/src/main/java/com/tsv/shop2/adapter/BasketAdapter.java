package com.tsv.shop2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tsv.shop2.R;
import com.tsv.shop2.entity.ShopEntity1;

import java.util.List;

public class BasketAdapter extends ArrayAdapter<ShopEntity1> {

        private LayoutInflater inflater;
        private int layout;
        private List<ShopEntity1> shopEntity1List;

        public BasketAdapter(Context context, int resource, List<ShopEntity1> shopEntity1List) {
            super(context, resource, shopEntity1List);
            this.shopEntity1List = shopEntity1List;
            this.layout = resource;
            this.inflater = LayoutInflater.from(context);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = inflater.inflate(this.layout, parent, false);

            TextView idView = view.findViewById(R.id.TV_id_tovara_tov_Backet);
            TextView nameView = view.findViewById(R.id.TV_name_tov_Backet);
            TextView priceView = view.findViewById(R.id.TV_price_tov_Backet);

            final ShopEntity1 shopEntity1 = shopEntity1List.get(position);

            idView.setText(String.valueOf(shopEntity1.getID()));
            nameView.setText(shopEntity1.getNameProduct());
            priceView.setText(String.valueOf(shopEntity1.getPriceProduct()));

            return view;
        }
    }

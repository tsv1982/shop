package com.tsv.shop2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsv.shop2.R;
import com.tsv.shop2.entity.ShopEntity1;

import java.util.List;

public class ShopAdapter extends ArrayAdapter<ShopEntity1> {

    private LayoutInflater inflater;
    private int layout;
    private List<ShopEntity1> shopEntity1List;

    public ShopAdapter(Context context, int resource, List<ShopEntity1> shopEntity1List) {
        super(context, resource, shopEntity1List);
        this.shopEntity1List = shopEntity1List;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView imageShopVie = view.findViewById(R.id.imageIV);
        TextView nameView = view.findViewById(R.id.nameIV);
        TextView sizeView = view.findViewById(R.id.sizeIV);
        TextView priceView = view.findViewById(R.id.priceIV);

        ImageView imageViewCheck = view.findViewById(R.id.imageCheckTov);

        final ShopEntity1 shopEntity1 = shopEntity1List.get(position);

        nameView.setText(shopEntity1.getNameProduct());
        sizeView.setText(shopEntity1.getSizeProduct());
        priceView.setText(String.valueOf(shopEntity1.getPriceProduct()));
        if (shopEntity1.getChechTov()) {
            imageViewCheck.setVisibility(View.VISIBLE);
        } else {
            imageViewCheck.setVisibility(View.INVISIBLE);
        }

        Bitmap myBitmap = BitmapFactory.decodeFile(shopEntity1.getImageProduct());
        imageShopVie.setImageBitmap(myBitmap);

        return view;
    }
}


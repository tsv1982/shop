package com.tsv.shop2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tsv.shop2.R;
import com.tsv.shop2.Util.Util;
import com.tsv.shop2.entity.Customer1;

import java.util.List;

public class ArchiveAdapter extends ArrayAdapter<Customer1> {

    private LayoutInflater inflater;
    private int layout;
    private List<Customer1> customer11List;
    private Util util = new Util();


    public ArchiveAdapter(Context context, int resource, List<Customer1> customer11List) {
        super(context, resource, customer11List);
        this.customer11List = customer11List;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView date_bay_archive = view.findViewById(R.id.date_bay_archive);
        TextView sum_price_aechive = view.findViewById(R.id.sum_price_arhive);

        Customer1 customer1 = customer11List.get(position);

        date_bay_archive.setText(customer1.getDateBay());

        String b = customer1.getIdBayTow();
        String s[] = b.split(",");

        double sum = 0;

        for (int i = 0; i < s.length; i++) {

            sum += util.getIdShop(view.getContext(), Integer.parseInt(s[i])).getPriceProduct();
        }

        sum_price_aechive.setText(String.valueOf(sum) + " грн");

        return view;
    }
}

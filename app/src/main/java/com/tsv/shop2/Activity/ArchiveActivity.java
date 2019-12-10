package com.tsv.shop2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.tsv.shop2.R;
import com.tsv.shop2.Util.Util;
import com.tsv.shop2.adapter.ArchiveAdapter;
import com.tsv.shop2.dagger.DaggerApp;
import com.tsv.shop2.entity.Customer1;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArchiveActivity extends AppCompatActivity {

    @Inject
    Util util;

    @BindView(R.id.LV_archive)
    ListView listViewArchive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        DaggerApp.getComponent().injects(this);

        List<Customer1> list = new ArrayList<>();

        for (int i = 0; i < util.getListCustomer(this).size(); i++) {
            Customer1 customer1 = util.getListCustomer(this).get(i);
            if (customer1.getIdShowTow().equals("0")) {
                list.add(customer1);
            }
        }

        ArchiveAdapter archiveAdapter = new ArchiveAdapter(this, R.layout.list_archive, list);
        listViewArchive.setAdapter(archiveAdapter);
    }
}

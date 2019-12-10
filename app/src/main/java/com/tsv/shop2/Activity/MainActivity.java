package com.tsv.shop2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tsv.shop2.R;
import com.tsv.shop2.Util.Util;
import com.tsv.shop2.adapter.ShopAdapter;
import com.tsv.shop2.dagger.DaggerApp;
import com.tsv.shop2.entity.ShopEntity1;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private ShopAdapter shopAdapter;
    private boolean aBoolean = true;
    private boolean positionBol = false;
    private int pisTov;
    private boolean onBootomDel = false;
    private boolean isFloat = true;
    private int coutnTow = 0;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fab_1)
    FloatingActionButton fab_1;
    @BindView(R.id.fab_2)
    FloatingActionButton fab_2;
    @BindView(R.id.fab_3)
    FloatingActionButton fab_3;
    @BindView(R.id.fab_4)
    FloatingActionButton fab_4;
    @BindView(R.id.LV_ShopLv)
    ListView listViewShop;
    @BindView(R.id.text_add)
    TextView textViewAdd;
    @BindView(R.id.text_delete)
    TextView textViewDelete;
    @BindView(R.id.text_all_check)
    TextView textViewAllCheck;
    @BindView(R.id.text_korzina)
    TextView textViewKorzina;
    @BindView(R.id.Tv_count_tw)
    TextView textViewCount;

    @OnClick({R.id.fab, R.id.fab_1, R.id.fab_2, R.id.fab_3, R.id.fab_4})
    void onSaveClick(View view) {
        switch (view.getId()) {

            case R.id.fab:
                if (aBoolean) {
                    fabShow();
                    listViewShop.setAlpha(0.3f);
                } else {
                    fabHide();
                    listViewShop.setAlpha(1f);
                }
                break;

            case R.id.fab_1:
                if (adminOrUser()) {
                    Intent intent = new Intent(this, AddActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent1 = new Intent(this, Start.class);
                    startActivity(intent1);
                    finish();
                }
                break;

            case R.id.fab_2:              // удалить
                if (adminOrUser()) {
                    if (onBootomDel) {
                        onBootomDel = false;
                    } else {
                        onBootomDel = true;
                    }
                    if (!positionBol) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "выберите товар", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        ShopEntity1 shopEntity1 = util.getListShopBd(this).get(pisTov);
                        File file = new File(shopEntity1.getImageProduct());
                        file.delete();
                        util.delete(shopEntity1, this);
                        shopAdapter.clear();
                        shopAdapter = new ShopAdapter(this, R.layout.list_shop, util.getListShopBd(this));
                        listViewShop.setAdapter(shopAdapter);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (pisTov != 0) {
                            listViewShop.setSelection(pisTov - 1);
                        }
                        positionBol = false;
                    }
                } else {
                    for (int i = 0; i < util.getListShopBd(this).size(); i++) {
                        checkFalse(this, i);
                    }
                }
                break;

            case R.id.fab_3:
                for (int i = 0; i < util.getListShopBd(this).size(); i++) {
                    checkFalse(this, i);
                }
                for (int i = 0; i < util.getListShopBd(this).size(); i++) {
                    check(this, i);
                }
                break;

            case R.id.fab_4:
                Intent intent1 = new Intent(this, Basket.class);
                startActivity(intent1);
//                finish();
                break;
        }
    }

    @Inject
    Util util;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        DaggerApp.getComponent().injects(this);

        fabHide();

        if (adminOrUser()) {
        } else {
            textViewDelete.setText("отменить выделение");
            textViewAdd.setText("выйти");
            fab_1.setImageResource(android.R.drawable.ic_lock_power_off);
        }

        shopAdapter = new ShopAdapter(this, R.layout.list_shop, util.getListShopBd(this));
        listViewShop.setAdapter(shopAdapter);   // сетаем адаптер в листвиев

        listViewShop.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    fab.show();
                    if (coutnTow != 0) {
                        textViewCount.setVisibility(View.VISIBLE);
                    }
                } else {
                    textViewCount.setVisibility(View.INVISIBLE);
                    fab.hide();
                    fabHide();
                    listViewShop.setAlpha(1f);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        listViewShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if (isFloat) {
                    check(v.getContext(), position);
                }

                fabHide();
                listViewShop.setAlpha(1f);

                pisTov = position;

                if (adminOrUser() && onBootomDel) {  // отметить для удал если админ
                    positionBol = true;
                    check(v.getContext(), pisTov);
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < util.getListShopBd(this).size(); i++) {
            checkFalse(this, i);
        }
    }

    void check(Context context, int pos) {
        ShopEntity1 shopEntity1 = util.getListShopBd(context).get(pos);
        if (shopEntity1.getChechTov()) {
            shopEntity1.setChechTov(false);
            if (coutnTow != 0) {
                coutnTow--;
                textViewCount.setText(String.valueOf(coutnTow));
            }
        } else {
            shopEntity1.setChechTov(true);
            coutnTow++;
            textViewCount.setText(String.valueOf(coutnTow));
        }
        util.udate(shopEntity1, context);

        shopAdapter.clear();
        shopAdapter = new ShopAdapter(context, R.layout.list_shop, util.getListShopBd(context));
        listViewShop.setAdapter(shopAdapter);

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listViewShop.setSelection(pos);
    }

    void checkFalse(Context context, int pos) {
        ShopEntity1 shopEntity1 = util.getListShopBd(context).get(pos);
        shopEntity1.setChechTov(false);
        if (coutnTow != 0) {
            coutnTow--;
            textViewCount.setText(String.valueOf(coutnTow));
        }
        util.udate(shopEntity1, context);
        shopAdapter.clear();
        shopAdapter = new ShopAdapter(context, R.layout.list_shop, util.getListShopBd(context));
        listViewShop.setAdapter(shopAdapter);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listViewShop.setSelection(pos);
    }

    boolean adminOrUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        return sharedPreferences.getString("IdOrUser", "").equals("admin");

    }

    void fabShow() {
        isFloat = false;
        fab_1.show();
        fab_2.show();
        textViewAdd.setVisibility(View.VISIBLE);
        textViewDelete.setVisibility(View.VISIBLE);

        if (!adminOrUser()) {
            fab_3.show();
            fab_4.show();
            textViewAllCheck.setVisibility(View.VISIBLE);
            textViewKorzina.setVisibility(View.VISIBLE);
        }
        aBoolean = false;
    }

    void fabHide() {
        isFloat = true;
        fab_1.hide();
        fab_2.hide();
        fab_3.hide();
        fab_4.hide();
        textViewAdd.setVisibility(View.INVISIBLE);
        textViewDelete.setVisibility(View.INVISIBLE);
        textViewAllCheck.setVisibility(View.INVISIBLE);
        textViewKorzina.setVisibility(View.INVISIBLE);
        aBoolean = true;
    }
}

package com.tsv.shop2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tsv.shop2.R;
import com.tsv.shop2.Util.Util;
import com.tsv.shop2.adapter.BasketAdapter;
import com.tsv.shop2.dagger.DaggerApp;
import com.tsv.shop2.entity.Customer1;
import com.tsv.shop2.entity.ShopEntity1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Basket extends AppCompatActivity {

    private BasketAdapter basketAdapter;
    private ArrayList<ShopEntity1> shopEntity1ArrayList = new ArrayList<>();
    private double sumPrice = 0;
    private boolean isFloat = true;
    private String nameCustomer;
    private String dateText;
    private String bayIdTow = "";
    private Customer1 customer1;

    @BindView(R.id.LV_BasketLv)
    ListView listViewBasket;
    @BindView(R.id.TV_Data_tov_Backet)
    TextView textViewData;
    @BindView(R.id.TV_sum_price)
    TextView textViewSumPrice;
    @BindView(R.id.BtnСancel)
    Button buttonCancel;
    @BindView(R.id.BtnBay)
    Button buttonBay;
    @BindView(R.id.fab_basket)
    FloatingActionButton fab_basket;
    @BindView(R.id.fab_1_basket)
    FloatingActionButton fab_basket_1;
    @BindView(R.id.fab_2_basket)
    FloatingActionButton fab_basket_2;
    @BindView(R.id.TV_nameCustomer)
    TextView textViewNameCusromer;
    @BindView(R.id.TV_backet_flo_delet)
    TextView textViewDelete;
    @BindView(R.id.TV_backet_flo_s)
    TextView textViewS;
    @BindView(R.id.TV_backet_flo_s_show)
    TextView textViewS_Show;
    @BindView(R.id.fab_2_basket_show)
    FloatingActionButton fab_basket_2_show;

    @Inject
    Util util;

    @OnClick({R.id.BtnСancel, R.id.BtnBay, R.id.fab_basket, R.id.fab_1_basket, R.id.fab_2_basket, R.id.fab_2_basket_show})
    void onSaveClick(View view) {
        switch (view.getId()) {
            case R.id.BtnСancel:
                customer1 = new Customer1();
                customer1.setIdCus(new Random().nextInt(1000));
                customer1.setNameCastomer(nameCustomer);
                customer1.setIdBayTow(bayIdTow);
                customer1.setIdShowTow("1");
                customer1.setDateBay(dateText);
                util.addCustomerBay(customer1, this);
                for (int i = 0; i < util.getListShopBd(this).size(); i++) {
                    checkFalse(this, i);
                }
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
//                finish();
                break;
            case R.id.BtnBay:
                customer1 = new Customer1();
                customer1.setIdCus(new Random().nextInt(1000));
                customer1.setNameCastomer(nameCustomer);
                customer1.setIdBayTow(bayIdTow);
                customer1.setIdShowTow("0");
                customer1.setDateBay(dateText);
                util.addCustomerBay(customer1, this);
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
//                finish();
                break;
            case R.id.fab_basket:
                if (isFloat) {
                    show();
                    isFloat = false;
                } else {
                    hide();
                    isFloat = true;
                }
                break;
            case R.id.fab_1_basket:
                for (int i = 0; i < util.getListCustomer(this).size(); i++) {
                    util.deleteCustomer(util.getListCustomer(this).get(i), this);
                }
                Toast toast = Toast.makeText(getApplicationContext(),
                        "архив покупок \n удален", Toast.LENGTH_SHORT);
                toast.show();

                break;
            case R.id.fab_2_basket:
                Intent intent3 = new Intent(this, ArchiveActivity.class);
                startActivity(intent3);
//                finish();

                break;
            case R.id.fab_2_basket_show:
                Intent intent4 = new Intent(this, ActivityShow.class);
                startActivity(intent4);
//                finish();

                break;
        }
    }

    void hide() {
        fab_basket_1.hide();
        fab_basket_2.hide();
        fab_basket_2_show.hide();
        textViewS.setVisibility(View.INVISIBLE);
        textViewDelete.setVisibility(View.INVISIBLE);
        textViewS_Show.setVisibility(View.INVISIBLE);

    }

    void show() {
        fab_basket_1.show();
        fab_basket_2.show();
        fab_basket_2_show.show();
        textViewS.setVisibility(View.VISIBLE);
        textViewDelete.setVisibility(View.VISIBLE);
        textViewS_Show.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        hide();

        DaggerApp.getComponent().injects(this);

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        dateText = dateFormat.format(currentDate);
        textViewData.setText(dateText);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        nameCustomer = sharedPreferences.getString("IdOrUser", "");
        textViewNameCusromer.setText(nameCustomer);

        List<ShopEntity1> list = util.getListShopBd(this);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getChechTov()) {
                shopEntity1ArrayList.add(list.get(i));
                sumPrice += list.get(i).getPriceProduct();
                bayIdTow += list.get(i).getID() + ",";
            }
        }
        if (shopEntity1ArrayList.size() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "корзина пустая", Toast.LENGTH_SHORT);
            toast.show();
            buttonBay.setVisibility(View.INVISIBLE);
        }
        textViewSumPrice.setText(String.valueOf(sumPrice));

        basketAdapter = new BasketAdapter(this, R.layout.list_basket, shopEntity1ArrayList);
        listViewBasket.setAdapter(basketAdapter);   // сетаем адаптер в листвиев
    }

    void checkFalse(Context context, int pos) {
        ShopEntity1 shopEntity1 = util.getListShopBd(context).get(pos);
        shopEntity1.setChechTov(false);
        util.udate(shopEntity1, context);
    }
}

package com.tsv.shop2.Util;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.tsv.shop2.Dao.AppDatabase;
import com.tsv.shop2.Dao.CustomerDao;
import com.tsv.shop2.Dao.ShopEntityDao;
import com.tsv.shop2.entity.Customer1;
import com.tsv.shop2.entity.ShopEntity1;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Util {

    public List<ShopEntity1> getListShopBd(Context context) {
        List<ShopEntity1> list1 = null;
        try {
            list1 = new AsyncTask<Void, Void, List<ShopEntity1>>() {
                @Override
                protected List<ShopEntity1> doInBackground(Void... voids) {
                    AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shopEntity1").build();
                    ShopEntityDao shopEntityDao = db.shopEntityDao();

                    List<ShopEntity1> list1 = shopEntityDao.getAll();

                    return list1;
                }
            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list1;
    }

    public ShopEntity1 getIdShop(Context context, int id){
        ShopEntity1 shopEntity1 = null;
        try {
            shopEntity1 = new AsyncTask<Void, Void, ShopEntity1>() {
                @Override
                protected ShopEntity1 doInBackground(Void... voids) {
                    AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shopEntity1").build();
                    ShopEntityDao shopEntityDao = db.shopEntityDao();
                   ShopEntity1 s = shopEntityDao.getById(id);
                    return s;
                }
            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return shopEntity1;
    }

    public void addShop(ShopEntity1 shopEntity1, Context context) {
        new AsyncTask<ShopEntity1, Void, Void>() {
            @Override
            protected Void doInBackground(ShopEntity1... shopEntities) {
                AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, "shopEntity1").build();
                ShopEntityDao shopEntityDao = db.shopEntityDao();
                shopEntityDao.insert(shopEntity1);
                return null;
            }
        }.execute();
    }

    public void delete(ShopEntity1 shopEntity1, Context context) {
        new AsyncTask<ShopEntity1, Void, Void>() {
            @Override
            protected Void doInBackground(ShopEntity1... shopEntities) {
                AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, "shopEntity1").build();
                ShopEntityDao shopEntityDao = db.shopEntityDao();
                shopEntityDao.delete(shopEntity1);
                return null;
            }
        }.execute();
    }

    public void udate(ShopEntity1 shopEntity1, Context context) {
        new AsyncTask<ShopEntity1, Void, Void>() {
            @Override
            protected Void doInBackground(ShopEntity1... shopEntities) {
                AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, "shopEntity1").build();
                ShopEntityDao shopEntityDao = db.shopEntityDao();
                shopEntityDao.update(shopEntity1);
                return null;
            }
        }.execute();
    }

    public void addCustomerBay(Customer1 customer1, Context context) {
        new AsyncTask<Customer1, Void, Void>() {
            @Override
            protected Void doInBackground(Customer1... customer1s) {
                AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, "customer1").build();
                CustomerDao customerDao = db.customerDao();
                customerDao.insert(customer1);
                return null;
            }
        }.execute();
    }

    public List<Customer1> getListCustomer(Context context) {
        List<Customer1> list1 = null;
        try {
            list1 = new AsyncTask<Void, Void, List<Customer1>>() {
                @Override
                protected List<Customer1> doInBackground(Void... voids) {
                    AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "customer1").build();
                    CustomerDao customerDao = db.customerDao();
                     List<Customer1> list1 = customerDao.getAll();

                    return list1;
                }
            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list1;
    }

    public void deleteCustomer(Customer1 customer1, Context context) {
        new AsyncTask<Customer1, Void, Void>() {
            @Override
            protected Void doInBackground(Customer1... customer1s) {
                AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, "customer1").build();
                CustomerDao customerDao = db.customerDao();
                customerDao.delete(customer1);
                return null;
            }
        }.execute();
    }

}

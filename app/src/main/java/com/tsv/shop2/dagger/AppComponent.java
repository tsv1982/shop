package com.tsv.shop2.dagger;

import com.tsv.shop2.Activity.ActivityShow;
import com.tsv.shop2.Activity.AddActivity;
import com.tsv.shop2.Activity.ArchiveActivity;
import com.tsv.shop2.Activity.Basket;
import com.tsv.shop2.Activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = UtilModul.class)
public interface AppComponent {
    void injects(MainActivity mainActivity);
    void injects(Basket basket);
    void injects(ArchiveActivity archiveActivity);
    void injects(AddActivity addActivity);
    void injects(ActivityShow activityShow);

}



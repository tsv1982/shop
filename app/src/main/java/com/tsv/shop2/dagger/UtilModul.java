package com.tsv.shop2.dagger;

import com.tsv.shop2.Util.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModul {

    @Provides
    @Singleton
    Util provideUtil() {
        return new Util();
    }
}

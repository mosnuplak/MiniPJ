package com.mfec.minipj;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by E5-473G on 7/23/2017.
 */

public class MiniPJApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());


        Realm.init(this);
//        initRealm();
    }

    private void initRealm() {

//        RealmConfiguration realmConfiguration  = new RealmConfiguration.Builder(this)
//                .name("android.realm")
//                .schemaVersion(0)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(realmConfiguration);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("mos.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(configuration);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

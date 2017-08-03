package com.mfec.minipj.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.mfec.minipj.R;
import com.mfec.minipj.fragment.MainFragment;
import com.mfec.minipj.util.ScreenUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLUE);
        }

        //int screenW = ScreenUtils.getInstance().getScreenWidth();
        //int screenH = ScreenUtils.getInstance().getScreenHeight();
        if (savedInstanceState == null) {
            //First Created
            //Fragment Here
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,new MainFragment().newInstance(123))
                    .commit();
        }
    }

}

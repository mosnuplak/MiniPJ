package com.mfec.minipj.activity;



import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mfec.minipj.R;
import com.mfec.minipj.dao.People;
import com.mfec.minipj.fragment.MainFragment;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by E5-473G on 7/24/2017.
 */

public class DiscriptionActivity extends AppCompatActivity {
    ImageView picPeo;
    TextView namePeo;
    TextView numClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disc);

        picPeo = (ImageView) findViewById(R.id.imgPic);
        namePeo = (TextView) findViewById(R.id.tvName);
        numClick = (TextView) findViewById(R.id.tvNumClick);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            int numclick = bundle.getInt("num");
            String picurl = bundle.getString("picurl");
            numclick+=1;
            namePeo.setText(name);
            numClick.setText(numclick+"");
            Glide.with(this)
                    .load(picurl)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .bitmapTransform(new CropCircleTransformation(getApplicationContext()))
                    .into(picPeo);

//            Toast.makeText(this,"Name :"+ name +
//                                "Numclick :" + numclick +
//                                "Picurl :" + picurl,Toast.LENGTH_SHORT)
//                                .show();
        }


    }

}

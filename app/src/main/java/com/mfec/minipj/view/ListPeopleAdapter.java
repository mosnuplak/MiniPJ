package com.mfec.minipj.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mfec.minipj.R;
import com.mfec.minipj.activity.DiscriptionActivity;
import com.mfec.minipj.dao.People;
import com.mfec.minipj.fragment.MainFragment;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by E5-473G on 7/24/2017.
 */

public class ListPeopleAdapter extends RecyclerView.Adapter<ListPeopleViewHolder>{
    List<People> peoplelist;
    MainFragment mainFragment;
    DiscriptionActivity discriptionActivity;
    public ListPeopleAdapter(List<People> peoplelist, MainFragment mainFragment){
        this.peoplelist = peoplelist;
        this.mainFragment = mainFragment;
    }
    @Override
    public ListPeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listpeople_main,parent,false);
        return new ListPeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListPeopleViewHolder holder, final int position) {
        holder.numCilck.setText(String.valueOf(peoplelist.get(position).getNumClick()));
        holder.namePeople.setText(peoplelist.get(position).getPeopleName());
        Glide.with(mainFragment.getActivity())
                .load(peoplelist.get(position).getPictureUrl())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .bitmapTransform(new CropCircleTransformation(mainFragment.getContext()))
                .into(holder.picPeople);
        //holder.listItem.
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(mainFragment.getActivity());
                builder.setMessage("ต้องการดูข้อมูล?");
                builder.setPositiveButton("ต้องการ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(mainFragment.getActivity(), DiscriptionActivity.class);
                        intent.putExtra("id",position);
                        intent.putExtra("name",peoplelist.get(position).getPeopleName());
                        intent.putExtra("num" , peoplelist.get(position).getNumClick());
                        intent.putExtra("picurl" , peoplelist.get(position).getPictureUrl());
                        mainFragment.startActivity(intent);
                        mainFragment.updataNumCilck(peoplelist.get(position));

                    }
                });
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });
                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return peoplelist.size();
    }
}

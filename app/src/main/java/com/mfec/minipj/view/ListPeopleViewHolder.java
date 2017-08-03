package com.mfec.minipj.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfec.minipj.R;

/**
 * Created by E5-473G on 7/24/2017.
 */

public class ListPeopleViewHolder extends RecyclerView.ViewHolder {
    LinearLayout listItem;
    ImageView picPeople;
    TextView namePeople, numCilck;
    public ListPeopleViewHolder(View itemView) {
        super(itemView);
        listItem = (LinearLayout) itemView.findViewById(R.id.list_item);
        picPeople = (ImageView) itemView.findViewById(R.id.pic_people);
        namePeople = (TextView) itemView.findViewById(R.id.name_people);
        numCilck =(TextView) itemView.findViewById(R.id.num_click);
    }
}

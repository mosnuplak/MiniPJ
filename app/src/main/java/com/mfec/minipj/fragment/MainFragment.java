package com.mfec.minipj.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mfec.minipj.R;
import com.mfec.minipj.dao.People;
import com.mfec.minipj.dao.PeopleItemDao;
import com.mfec.minipj.manager.HttpManager;
import com.mfec.minipj.view.ListPeopleAdapter;

import java.io.IOException;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;
/**
 * Created by E5-473G on 7/23/2017.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private int index = 0;
    int someVar;
    private Realm realm;
    EditText realmList;
    RecyclerView listview;
    ListPeopleAdapter adapter;
    Button btOne;
    Button btTwo;
    Button btSeach;


    public static MainFragment newInstance(int sumVar) {
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle(); // Arguments
        args.putInt("someVar" , sumVar);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Read from Arguments

        realm = Realm.getDefaultInstance();
        someVar = getArguments().getInt("someVar");



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        hideKeyboard();

        View rootView = inflater.inflate(R.layout.fragment_main,
                container, false);
        initInstances(rootView);

        btOne.setOnClickListener(this);
        btTwo.setOnClickListener(this);
        btSeach.setOnClickListener(this);




        realmList.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    showPeopleBySeach(realmList.getText().toString());
                    imm.hideSoftInputFromWindow(getActivity()
                            .getCurrentFocus()
                            .getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    private void hideKeyboard() {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }



    private void initInstances(View rootView) {
        // findViewById


        realmList = (EditText) rootView.findViewById(R.id.realm_list);
        listview = (RecyclerView) rootView.findViewById(R.id.list_item);
        btOne = (Button) rootView.findViewById(R.id.bt_one);
        btTwo = (Button) rootView.findViewById(R.id.bt_two);
        btSeach = (Button) rootView.findViewById(R.id.btn_search);

        Call<List<PeopleItemDao>> call = HttpManager.getInstance().getService().LoadPerpeoList();
        call.enqueue(new Callback<List<PeopleItemDao>>() {
            @Override
            public void onResponse(Call<List<PeopleItemDao>> call,
                                   Response<List<PeopleItemDao>> response) {
                if (response.isSuccessful()) {
                    List<PeopleItemDao> dao = response.body();
                    clearPeople();
                    insertToRealm(dao);

                } else  {
                    try {

                        Toast.makeText(getActivity(),
                                response.errorBody().string(),
                                Toast.LENGTH_LONG)
                                .show();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PeopleItemDao>> call,
                                  Throwable t) {
                Toast.makeText(getActivity(),
                        t.toString(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void insertToRealm(final List<PeopleItemDao> dao) {
        realm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {


                        for (PeopleItemDao p : dao) {

                            People people = realm.createObject(People.class , ++index);
//                            people.setPeopleId(++index);
                            people.setPeopleName(p.getKnownAsName());
                            people.setPictureUrl(p.getImageUrl());
                            people.setNumClick(0);
                        }



                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        showPeople(1);

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }
                });
    }

    private void showPeople(int grid) {

        RealmResults<People> listPeople = getAllStudent();

        if (grid == 1) {
            GridLayoutManager manager = new GridLayoutManager(getContext().getApplicationContext(), 1);
            listview.setLayoutManager(manager);
        } else {
            GridLayoutManager manager = new GridLayoutManager(getContext().getApplicationContext(), 2);
            listview.setLayoutManager(manager);
        }

        adapter = new ListPeopleAdapter(listPeople,MainFragment.this);
        listview.setAdapter(adapter);
        //realmList.setText(str);




    }
    private void clearPeople() {
        realm.beginTransaction();
        realm.delete(People.class);
        realm.commitTransaction();
    }

    private RealmResults<People> getAllStudent() {
        RealmResults<People> result = realm.where(People.class).findAll();
        return result;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


        if (savedInstanceState != null) {
            //Restore State here
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
    public void updataNumCilck(People ppl){
        final People people = new People();
        people.setPeopleId(ppl.getPeopleId());
        people.setPictureUrl(ppl.getPictureUrl());
        people.setPeopleName(ppl.getPeopleName());
        people.setNumClick(ppl.getNumClick()+1);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(people);
                adapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        switch ( v.getId() ) {
            case R.id.bt_one : showPeople(1);
                //hide keyboard
                imm.hideSoftInputFromWindow(getActivity()
                        .getCurrentFocus()
                        .getWindowToken(), 0);
                        break;
            case R.id.bt_two : showPeople(2);
                //hide keyboard
                imm.hideSoftInputFromWindow(getActivity()
                        .getCurrentFocus()
                        .getWindowToken(), 0);
                        break;
            case R.id.btn_search : showPeopleBySeach(realmList.getText().toString());
                imm.hideSoftInputFromWindow(getActivity()
                        .getCurrentFocus()
                        .getWindowToken(), 0);
                        break;

        }

    }

    private void showPeopleBySeach(String text) {
        RealmResults<People> result = realm.where(People.class).like("peopleName","*"+text+"*", Case.INSENSITIVE).findAll();
        if (result.size() == 0) {
            Toast.makeText(getActivity(),
                   "ไม่มี",
                    Toast.LENGTH_LONG)
                    .show();
        }
        GridLayoutManager manager = new GridLayoutManager(getContext().getApplicationContext(), 1);
        listview.setLayoutManager(manager);
        adapter = new ListPeopleAdapter(result,MainFragment.this);
        listview.setAdapter(adapter);

    }
}

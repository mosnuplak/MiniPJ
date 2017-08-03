package com.mfec.minipj.manager.http;

import com.mfec.minipj.dao.PeopleItemDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

/**
 * Created by E5-473G on 7/23/2017.
 */

public interface ApiService {
    @GET("public/artist/findAll")
    Call<List<PeopleItemDao>> LoadPerpeoList();

}

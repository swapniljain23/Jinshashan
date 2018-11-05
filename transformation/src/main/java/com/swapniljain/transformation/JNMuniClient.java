package com.swapniljain.transformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JNMuniClient {

    @GET("android/listMunis.php")
    Call<List<JNMuniModel>> getMuniList();

}

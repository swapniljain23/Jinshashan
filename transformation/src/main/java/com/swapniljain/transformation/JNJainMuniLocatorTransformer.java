package com.swapniljain.transformation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JNJainMuniLocatorTransformer {

    public static void main(String args[]) {
        JNJainMuniLocatorTransformer transformer = new JNJainMuniLocatorTransformer();
        transformer.fetchMuniList();
    }

    List<JNMuniModel> mMuniList = new ArrayList<JNMuniModel>();

    public void fetchMuniList() {
        JNMuniClient client = new JNRetrofitClient().getClient().create(JNMuniClient.class);
        Call<List<JNMuniModel>> call = client.getMuniList();
        call.enqueue(new Callback<List<JNMuniModel>>() {
            @Override
            public void onResponse(Call<List<JNMuniModel>> call,
                                   Response<List<JNMuniModel>> response) {
                mMuniList = response.body();
                //System.out.print(mMuniList);
                parseToJSON();
            }

            @Override
            public void onFailure(Call<List<JNMuniModel>> call, Throwable t) {
                System.out.print("onFailure");
            }
        });
    }

    public void parseToJSON() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        HashMap<String, JNListDataModel> dataModel = new HashMap<>();

        for (JNMuniModel muniModel: mMuniList) {
            JNListDataModel listModel = new JNListDataModel();

            listModel.recentInfo.address = muniModel.getLocation();
            listModel.dikshaInfo.dikshaName =
                    String.format("%s %s", muniModel.getUname(), muniModel.getName());
            listModel.sect.sect1 = "Digambar";
            listModel.personalInfo.fullName = muniModel.getBirthname();
            listModel.personalInfo.dateOfBirth = convertDate(muniModel.getDob());
            listModel.personalInfo.fatherNmae = muniModel.getFather();
            listModel.personalInfo.motherName = muniModel.getMother();
            listModel.personalInfo.education = muniModel.getEducation();
            if (muniModel.getSuffix().equalsIgnoreCase("Ji Maharaj")) {
                listModel.personalInfo.gender = "Male";
            } else if (muniModel.getSuffix().equalsIgnoreCase("Mata Ji")) {
                listModel.personalInfo.gender = "Female";
            } else {
                listModel.personalInfo.gender = "N/A";
            }
            listModel.photoURL = muniModel.getImgUrl();

            dataModel.put(Integer.toString(muniModel.getMid()), listModel);
        }

        String jsonString = gson.toJson(dataModel);
        System.out.print(jsonString);
    }

    public String convertDate(String dateToConvert) {
        String convertedDate = "";
        if (dateToConvert.equalsIgnoreCase("0000-00-00")) {
            return convertedDate;
        }
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat expectedDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        try {
            convertedDate = expectedDateFormat.format(currentDateFormat.parse(dateToConvert));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return convertedDate;
    }
}

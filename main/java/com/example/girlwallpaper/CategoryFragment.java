package com.example.girlwallpaper;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CategoryFragment extends Fragment {


    private RecyclerView recyclerView;
    private ArrayList<ImgModel> images;
    private ImgAdapter imgAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        images = new ArrayList<ImgModel>();
        /*images.add(new ImgModel("1.jpg"));
        images.add(new ImgModel("2.jpg"));
        images.add(new ImgModel("3.jpg"));
        images.add(new ImgModel("4.jpg"));
        images.add(new ImgModel("5.jpg"));
        images.add(new ImgModel("6.jpg"));
        images.add(new ImgModel("7.jpg"));
        images.add(new ImgModel("8.jpg"));*/
        imgAdapter = new ImgAdapter(getActivity(), images);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(imgAdapter);

        String url = "http://192.168.43.229/girl/images.php";
        new GetAllImages().execute(url);

        return view;
    }


    private class GetAllImages extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {


            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(strings[0]).build();
            try {
                okhttp3.Response response = okHttpClient.newCall(request).execute();
                String data = response.body().string();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @SuppressLint("LongLogTag")
        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

               try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                //             images = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    ImgModel imgModel = new ImgModel();
                    imgModel.setImgName(jsonObject1.getString("name"));

                    images.add(imgModel);

                 //   Log.i("ImgName===========>>>>>>>>>>>>", imgModel.getImgName());
                }


                imgAdapter.notifyDataSetChanged();

                //        recyclerView.setAdapter(imgAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}


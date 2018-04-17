package com.example.girlwallpaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dell on 05-04-2018.
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ImgViewHolder> {
    private Context context;
    private ArrayList<ImgModel> models;

    public ImgAdapter(Context context, ArrayList<ImgModel> models) {
        this.context = context;
        this.models = models;
    }


    @Override
    public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        return new ImgViewHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(ImgViewHolder holder, int position) {
     /* final ImgModel imgModel=models.get(position);
      holder.img.setImageURI(Uri.parse(imgModel.getImgName()));*/
        String url = "http://192.168.43.229/girl/" + models.get(position).getImgName();
    //    Log.i("url===========>>>>>>>>>>",url);
        Picasso.with(context).load(url).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ImgViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ImgViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);

        }
    }
}

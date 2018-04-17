package com.example.girlwallpaper;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment {


    public SubCategoryFragment() {
        // Required empty public constructor
    }
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sub_category, container, false);
        bottomNavigationView = view.findViewById(R.id.navigation);


        bottomNavigationView = view.findViewById(R.id.navigation);




        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener()

                {
                    public boolean onNavigationItemSelected (@NonNull MenuItem item){
                        switch (item.getItemId()) {
                            case R.id.action_item1:

                                String iconsStoragePath = Environment.getExternalStorageDirectory() + "/myAppDir/myImages/";
                                File sdIconStorageDir = new File(iconsStoragePath);
                                sdIconStorageDir.mkdirs();

                                String filePath = sdIconStorageDir.toString()+"Quotes";
                                FileOutputStream fileOutputStream = null;
                                try {
                                    fileOutputStream = new FileOutputStream(filePath);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
                                Bitmap imageData;
                                bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
                                try {
                                    bos.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    bos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case R.id.action_item3:
                                Toast.makeText(context, "select3", Toast.LENGTH_SHORT).show();
                                Uri uri = Uri.parse("market://details?id=com.google.android.apps.maps");
                                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                try {
                                    startActivity(goToMarket);
                                } catch (ActivityNotFoundException e) {
                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps")));
                                }
                                break;
                            case R.id.action_item4:
                                Toast.makeText(context, "select4", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
        return view;
    }

}

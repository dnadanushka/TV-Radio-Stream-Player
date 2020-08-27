package com.example.youtube_streamer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class DeshanaActivity extends AppCompatActivity {
    private static final String TAG = "DeshanaActivity";




    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText(DeshanaActivity.this, "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshana);

        MyListData[] myListData = new MyListData[20];

        ArrayList<String> mp3List = new ArrayList<>();

        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            ContentResolver cr = this.getContentResolver();

            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
            String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";




            Cursor cur = cr.query(uri, null, selection, null, sortOrder);
            int count = 0;

            if(cur != null)
            {
                count = cur.getCount();

                if(count > 0)
                {
                    while(cur.moveToNext())
                    {
                        String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                        Log.d(TAG, "onCreate: + " + data);
                        mp3List.add(data);
                    }

                }

                cur.close();
            }
        }

        for(int i  = 0 ; i<mp3List.size();i++){
            myListData[i] = new MyListData(mp3List.get(i));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.file_view);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }


//    ArrayList<HashMap<String,String>> getPlayList(String rootPath) {
//        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();
//
//
//        try {
//            File rootFolder = new File(rootPath);
//            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
//            for (File file : files) {
//                if (file.isDirectory()) {
//                    if (getPlayList(file.getAbsolutePath()) != null) {
//                        fileList.addAll(getPlayList(file.getAbsolutePath()));
//                    } else {
//                        break;
//                    }
//                } else if (file.getName().endsWith(".mp3")) {
//                    HashMap<String, String> song = new HashMap<>();
//                    song.put("file_path", file.getAbsolutePath());
//                    song.put("file_name", file.getName());
//                    Log.d(TAG, "getPlayList: "+file.getName());
//
//                    fileList.add(song);
//                }
//            }
//            return fileList;
//        } catch (Exception e) {
//            return null;
//        }
//    }
}

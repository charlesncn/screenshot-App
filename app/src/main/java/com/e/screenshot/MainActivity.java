package com.e.screenshot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[] {WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
        PackageManager.PERMISSION_GRANTED);


    }

    public void screenshotbtn(View view){
        View view1 = getWindow().getDecorView().getRootView();
        view1.setDrawingCacheEnabled(true);

        Bitmap bitmap = Bitmap.createBitmap(view1.getDrawingCache());

        view1.setWillNotDraw(false);

        String filepath = Environment.getExternalStorageDirectory()+"/Download/"+ Calendar.getInstance().getTime().toString()+".jpg";
        File fileScreenshot = new File(filepath);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream= new FileOutputStream(fileScreenshot);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
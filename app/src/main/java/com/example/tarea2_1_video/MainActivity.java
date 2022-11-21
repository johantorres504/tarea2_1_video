package com.example.tarea2_1_video;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{

    private VideoView videoP;
    private Spinner sp;
    private String[] lista;
    private static final int GRABACION_VIDEO = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoP = findViewById(R.id.videoP);
        sp = findViewById(R.id.spinner);
        lista = fileList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        sp.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)

    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GRABACION_VIDEO && resultCode == RESULT_OK)
        {
            Uri videoUri = data.getData();
            videoP.setVideoURI(videoUri);
            videoP.start();

            try {
                AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                FileInputStream in = videoAsset.createInputStream();
                FileOutputStream archivo = openFileOutput(crearNombreVideo(), Context.MODE_PRIVATE);
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0)
                {
                    archivo.write(buf, 0, len);
                }

            } catch (IOException e)
            {
                Toast.makeText(this, "Error ", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void Grabar_Video(View v)
    {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, GRABACION_VIDEO);
    }

    public void reproducirVideo(View v)
    {
        int pos=sp.getSelectedItemPosition();
        videoP.setVideoPath(getFilesDir()+"/"+lista[pos]);
        videoP.start();
    }
    
    private String crearNombreVideo()
    {

        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre = fecha + ".mp4";
        return nombre;
    }



}
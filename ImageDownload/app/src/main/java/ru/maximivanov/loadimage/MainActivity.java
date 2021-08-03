package ru.maximivanov.loadimage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    Button bStart;
    ImageView imageView;
    static Handler handler;
    Bitmap bitmap;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStart = findViewById(R.id.btStart);
        imageView = findViewById(R.id.img);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new AnotherRunnable());
                thread.start();
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                imageView.setImageBitmap(bitmap);
                imageView.invalidate();
            }
        };
    }

    class AnotherRunnable implements Runnable {
        @Override
        public void run() {
            try {
                URL url = new URL("https://i.content-review.com/s/cedd445c31116e7f96a9463cb8e2435d.png");
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
                handler.sendEmptyMessage(1);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
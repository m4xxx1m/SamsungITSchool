package ru.maximivanov.mydraw;

import android.graphics.Point;
import android.view.Display;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    public static int width;
    public static int height;
    public static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Display d = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        d.getSize(size);
        width = size.x;
        height = size.y;
        fm = getSupportFragmentManager();
        setContentView(new MyGame(this));
    }
}
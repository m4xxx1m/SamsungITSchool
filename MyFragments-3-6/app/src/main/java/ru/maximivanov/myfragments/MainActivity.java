package ru.maximivanov.myfragments;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private MyFragmentDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new MyFragmentDialog();
        fm = getSupportFragmentManager();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but1:
                Toast.makeText(MainActivity.this, "Кнопка нажата", Toast.LENGTH_LONG).show();
                break;
            case R.id.but2:
                try {
                    dialog.show(fm, "dialog");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
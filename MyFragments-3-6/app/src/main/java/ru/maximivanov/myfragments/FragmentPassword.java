package ru.maximivanov.myfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

public class FragmentPassword extends Fragment {
    public FragmentPassword() {

    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup container, Bundle savedInstanceState) {
        return li.inflate(R.layout.fragment_password, container, false);
    }
}

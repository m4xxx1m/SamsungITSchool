package ru.maximivanov.myfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class FragmentButton extends Fragment {
    public FragmentButton() {

    }
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup container, Bundle savedInstanceState) {
        return li.inflate(R.layout.fragment_button, container, false);
    }
}

package ru.maximivanov.myfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;

public class MyFragmentDialog extends DialogFragment implements DialogInterface.OnClickListener {
    public MyFragmentDialog() {

    }
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
            .setTitle("Пароль")
            .setPositiveButton("OK", this);
        try {
            EditText et = getActivity().findViewById(R.id.password);
            adb.setMessage(et.getText().toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}

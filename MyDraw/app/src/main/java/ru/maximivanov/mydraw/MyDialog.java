package ru.maximivanov.mydraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private int score;

    public MyDialog(int score) {
        this.score = score;
    }

    @Override
    public Dialog onCreateDialog(Bundle b) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Game Over!")
                .setPositiveButton("Restart", this)
                .setNegativeButton("Exit", this)
                .setMessage(String.valueOf(score));
        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                MyGame.isGameOver = false;
                break;
            case Dialog.BUTTON_NEGATIVE:
                System.exit(0);
                break;
        }
    }
}

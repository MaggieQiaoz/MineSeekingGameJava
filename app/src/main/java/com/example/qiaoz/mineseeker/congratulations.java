package com.example.qiaoz.mineseeker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

//activity page of the pop congratulation dialog when user find all mines.
public class congratulations extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       //create the view
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.congratulate_message_layout,null);

        // button listener
        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=MainActivity.makeIntent(getContext());
                startActivity(intent);
            }
        };
        //build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("congratulations message.")
                .setView(v)
                .setPositiveButton(android.R.string.ok,listener)
                .create();
    }
}

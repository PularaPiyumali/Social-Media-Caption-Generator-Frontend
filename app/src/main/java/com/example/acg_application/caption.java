package com.example.acg_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class caption extends AppCompatActivity {

    private ImageButton button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caption_generator);

        button = findViewById(R.id.imageButton);
        editText = findViewById(R.id.editTextTextMultiLine);

        button.setOnClickListener(view -> {

            String output = editText.getText().toString();
            if (output.isEmpty()) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();}
            else {
            String textToCopy = editText.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", textToCopy);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this,"The caption copied to clipboard",Toast.LENGTH_SHORT).show();}

        } );
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(caption.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(caption.this,selectionActivity.class);
                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }
}
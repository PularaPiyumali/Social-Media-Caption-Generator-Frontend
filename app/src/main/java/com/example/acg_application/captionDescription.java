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

public class captionDescription extends AppCompatActivity {

    private ImageButton button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

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
}

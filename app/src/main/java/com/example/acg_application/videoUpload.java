package com.example.acg_application;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class videoUpload extends AppCompatActivity {

    private static final String TAG = "videoUpload";

    public Button button;
    private Button videoButton;
    private VideoView videoView;

    private ActivityResultLauncher<Intent> videoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        // Get the video Uri from the gallery
                        Uri videoUri = data.getData();

                        // Get the file name from the video Uri
                        String videoFileName = getFileNameFromUri(videoUri);

                        // Create a new file in the app's private external storage directory
                        File videoFile = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), videoFileName);
                        FileOutputStream outputStream = null;
                        try {
                            outputStream = new FileOutputStream(videoFile);
                            InputStream inputStream = getContentResolver().openInputStream(videoUri);
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = inputStream.read(buffer)) > 0) {
                                outputStream.write(buffer, 0, length);
                            }
                            outputStream.close();
                            inputStream.close();
                            // Show a Toast message indicating that the video was saved
                            Toast.makeText(this, "Video saved to " + videoFile.getPath(), Toast.LENGTH_SHORT).show();

                            // Play the saved video in the VideoView
                            playVideo(videoFile.getPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
    );

    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        String[] projection = {MediaStore.MediaColumns.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
            fileName = cursor.getString(columnIndex);
            cursor.close();
        }
        return fileName;
    }

    private void playVideo(String videoPath) {
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_upload);

        videoButton = findViewById(R.id.videoButton);
        videoView = findViewById(R.id.gallery);

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                videoLauncher.launch(Intent.createChooser(intent, "Select Video"));
            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(videoUpload.this,selectButton.class);
                startActivity(intent);
            }
        });
    }
}
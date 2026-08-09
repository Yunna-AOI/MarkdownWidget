package com.yunnaaoi.mdwidget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.text.android.TextLayout;

import com.yunnaaoi.mdwidget.databinding.ActivityMainBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    Button btn_choose_file;
    TextView txt_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Widget", "MainActivity");

        btn_choose_file = findViewById(R.id.dummy_button);
        btn_choose_file.setOnClickListener(this::ChooseFile);

        txt_layout = findViewById(R.id.fullscreen_content);
    }


    void ChooseFile(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        Intent i = Intent.createChooser(intent, "View default file manager");
        startActivity(i);

        ActivityResultLauncher<String> mGet = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        ReadFile.readFromFile(this, uri);
                    }
                });
    }



}
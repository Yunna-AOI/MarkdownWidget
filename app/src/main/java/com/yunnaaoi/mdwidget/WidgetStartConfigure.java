package com.yunnaaoi.mdwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class WidgetStartConfigure extends AppCompatActivity {
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        Log.d("YunnaWidget", "WidgetStartConf");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null ) {
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        Button getData = (Button)findViewById(R.id.getData);
        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            EditText txt = findViewById(R.id.pathGetter);

            String note = txt.getText().toString();
            String color = "#0f0f12";

            Set<String> gset = new HashSet<String>();
            gset.add(note);
            Set<String> aset = new HashSet<String>();
            aset.add(color);


            WidgetPreference.saveSettings(this, appWidgetId, "note", gset);
            WidgetPreference.saveSettings(this, appWidgetId, "color", aset);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            WidgetMD.updateAppWidget(this, appWidgetManager, appWidgetId);

            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        });
    }
}

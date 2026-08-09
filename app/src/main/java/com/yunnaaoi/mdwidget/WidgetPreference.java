package com.yunnaaoi.mdwidget;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class WidgetPreference {

    // стоит добавить возможность хранить данные:
//    key: widget-id
//    values: meta-info-file
//            hash-sum of file
//            path/to/file
//            json->string: [color, background-color/image] (for future support)
//    SharedPreferense save as string (it may be bad tip)
    private static final String PREF_NAME = "widget_prefs";
    public static void saveSettings(Context context, int appWidgetId, String key, Set<String> value) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putStringSet(key + "_" + appWidgetId, value).apply();
    }

    public static Set<String> loadSettings(Context context, int appWidgetId, String key, Set<String> defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getStringSet(key+"_"+appWidgetId, defaultValue);
    }

    public static void removeSettingsForWidget(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

    }
}

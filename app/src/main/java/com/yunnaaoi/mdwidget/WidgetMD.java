package com.yunnaaoi.mdwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetMD extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Set<String> gset = new HashSet<String>();
        gset.add("default note");
        Set<String> aset = new HashSet<String>();
        aset.add("#FFFFFF");
        Set<String>  note = WidgetPreference.loadSettings(context, appWidgetId, "note", gset);
        Set<String> color = WidgetPreference.loadSettings(context, appWidgetId, "color", aset);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        views.setTextViewText(R.id.openApplication, note.toArray(new String[0])[0]);

        // Instruct the widget manager to update the widget
        Intent intent = new Intent(context, WidgetStartConfigure.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                appWidgetId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
        );
        views.setOnClickPendingIntent(R.id.openApplication, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetID: appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetID);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
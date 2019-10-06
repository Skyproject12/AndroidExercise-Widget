package com.example.widgethomescreen;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class RandomNumberWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // berfungsi mengambil layout
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_number_widget);

        // class Number Generator with generate 100
        String lastUpdate= "Random" + NumberGenerator.Generate(100);
        // call function random dan set fungsi tersebut kedalam widget dengan id appwidget_text
        views.setTextViewText(R.id.appwidget_text, lastUpdate);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    // method ini digunakan ketika widget pertama kali dipanggil
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // digunakan untuk melakukan perulangan yang mana widget yang akan aktif
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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


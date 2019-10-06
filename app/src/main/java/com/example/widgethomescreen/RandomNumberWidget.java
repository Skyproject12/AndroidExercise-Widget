package com.example.widgethomescreen;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class RandomNumberWidget extends AppWidgetProvider {

    private static String WIDGET_CLICK = "widgetsclick";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // berfungsi mengambil layout
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_number_widget);

        // class Number Generator with generate 100
        String lastUpdate = "Random" + NumberGenerator.Generate(100);
        // call function random dan set fungsi tersebut kedalam widget dengan id appwidget_text
        views.setTextViewText(R.id.appwidget_text, lastUpdate);
        // set onclick button  with function pending intent
        views.setOnClickPendingIntent(R.id.button_click, getPendingSelfIntent(context, appWidgetId, WIDGET_CLICK)); // mengeset action berupan WIDGET_CLICK agar dienali pada receiver
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


    // sebagai media penerima broadcahc yang di kirim dari method getPendingSelfIntent
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        // ketika WIDGET CLICK equals intent getAction
        if (WIDGET_CLICK.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_number_widget);
            String lastUpdate = "Random" + NumberGenerator.Generate(100);
            // mengambil kiriman dari getPendingSelfIntent
            int appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0);
            views.setTextViewText(R.id.appwidget_text, lastUpdate);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private static String WIDGET_ID_EXTRA = "widget_id_extra";
     static PendingIntent getPendingSelfIntent(Context context, int appWidgetId, String action){
         //intent to random number widget
        Intent intent= new Intent(context, RandomNumberWidget.class);
        // menambahkan inetnt put extra untuk mengidentifikasi widget yang akan dieksekusi
        intent.setAction(action);
        intent.putExtra(WIDGET_ID_EXTRA, appWidgetId);
        // ketika melakukan suatu broadcach tentu harus ada receivernya
        return PendingIntent.getBroadcast(context, appWidgetId, intent, 0);
    }
}
// jadi alurnya
//lakukan click buttom maka akan mengeksekusi method getPendingSelftIntent
// method tersebut akan memberi retur berupa broadcahs
// nantinya akan diterima pada receiver dan mengubah dari textview berdasarkan variable lastUpdate

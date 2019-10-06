package com.example.widgethomescreen;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class UpdateWidgetService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        AppWidgetManager manager= AppWidgetManager.getInstance(this);
        // mengeset layout view dari widget
        RemoteViews view= new RemoteViews(getPackageName(), R.layout.random_number_widget);
        // membuat component object dari class T=RandomNumberwidget
        ComponentName theWidget = new ComponentName(this, RandomNumberWidget.class);
        String lastUpdate= "Random:"+NumberGenerator.Generate(100);
        // mengeset text vview dengan random
        view.setTextViewText(R.id.appwidget_text, lastUpdate);
        // mengeset object manager
        manager.updateAppWidget(theWidget, view);
        // job finished
        jobFinished(jobParameters, false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}

package com.example.widgethomescreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int JOB_ID=100;
    // 3 menit
    private static final int SCHEDULE_OF_PERIOD= 86000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStart= findViewById(R.id.button_start);
        Button buttonStop= findViewById(R.id.button_stop);
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch(id){
            case R.id.button_start:
                startJob();
                break;
            case R.id.button_stop:
                cancelJob();
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    // memulai proses random
    private void startJob(){
        // membuat componnet object dari service
        ComponentName serviceComponent = new ComponentName(this, UpdateWidgetService.class);
        // mengeset random pada bouilder
        JobInfo.Builder builder= new JobInfo.Builder(JOB_ID, serviceComponent);
        // set builder
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
        // mengeset interfal random pada android n sebanyak lima belas menit
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            // memberi delay 15 menit
            builder.setPeriodic(900000L);
        }
        else{
            // memberi delay 3 menit
            builder.setPeriodic(SCHEDULE_OF_PERIOD);
        }
        // mengeset systen service dari job scheduler
        JobScheduler jobScheduler= (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if(jobScheduler !=null){
            // memberi dan menjalankan scheduler
            jobScheduler.schedule(builder.build());
        }
        Toast.makeText(this, "Job Service started", Toast.LENGTH_SHORT).show();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    // memberhentikan proses random pada widget
    private void cancelJob(){
        JobScheduler job= (JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if(job!=null){
            job.cancel(JOB_ID);
        }
        Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show();
    }
}

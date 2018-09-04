package javaherian.yousef.entekhabvahed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import com.magnetadservices.sdk.MagnetMobileBannerAd;
import com.magnetadservices.sdk.MagnetSDK;

import heidari.mohammad.entekhabvahed.ActivityShowRule;
import heidari.mohammad.entekhabvahed.ActivityShowSchedule;
//import heidari.mohammad.entekhabvahed.MusicManager;
import hoosmand.Database;
import process.processClass;
//import heidari.mohammad.entekhabvahed.HomeWatcher;

public class ActivityMenu extends AppCompatActivity implements View.OnClickListener {
    private Button btnInputCourse;
    private Button btnInputRule;
    private Button btnScheduleViewer;
    private Button btnAboutUs;
    private FrameLayout adLayout;
    public ActivityMenu activity;
    private Intent svc;
    DialogContactUs dialogContactUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        initClickOnListeners();
        /*
          creating the global database
          and the main class for handling processes
          these lines may change in future
         */
        Global.db = new Database(this);
        Global.mainProcess = new processClass(this);
        dialogContactUs =new DialogContactUs(this) ;
        MagnetSDK.initialize(getApplicationContext());
        /**
         * deactivate this before release
         */
      //  MagnetSDK.getSettings().setTestMode(true);
        MagnetMobileBannerAd bannerAd = MagnetMobileBannerAd.create(getApplicationContext());
        bannerAd.load("53c55294125a08d6a9a43c1a243c0313", adLayout);
    }
    private void findViews(){
        btnInputCourse=findViewById(R.id.btn_input_course);
        btnInputRule=findViewById(R.id.btn_input_rule);
        btnScheduleViewer=findViewById(R.id.btn_schedule_viewer);
        btnAboutUs=findViewById(R.id.btn_about_us);
        adLayout = findViewById(R.id.mobileBanner);
    }
    private void initClickOnListeners() {
        btnInputCourse.setOnClickListener(this);
        btnInputRule.setOnClickListener(this);
        btnScheduleViewer.setOnClickListener(this);
        btnAboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == null)
            return;
        if (view.getId()==R.id.btn_input_course){
            Intent intent=new Intent(this,ActivityViewCourses.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.btn_input_rule){
            Intent intent=new Intent(this, ActivityShowRule.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.btn_schedule_viewer){
            if (Global.mainProcess.scheduleSize()!=0) {
                Intent intent=new Intent(this, ActivityShowSchedule.class);
                startActivity(intent);
            }
            else {
                Intent intent=new Intent(this, ActivityNoSchedule.class);
                startActivity(intent);
            }
        }
        else if(view.getId()==R.id.btn_about_us){
            dialogContactUs.show();
        }

    }
}

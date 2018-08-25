package javaherian.yousef.entekhabvahed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import heidari.mohammad.entekhabvahed.ActivityShowRule;
import heidari.mohammad.entekhabvahed.MusicManager;
import hoosmand.Database;

public class ActivityMenu extends AppCompatActivity implements View.OnClickListener {
    private Button btnInputCourse;
    private Button btnInputRule;
    private Button btnScheduleViewer;
    private Button btnAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        initListeners();
        Intent svc=new Intent(this, MusicManager.class);
        startService(svc);
        /**
         * creating the global database
         * these lines may change in future
         */
        Global.db = new Database(this);
    }
    private void findViews(){
        btnInputCourse=findViewById(R.id.btn_input_course);
        btnInputRule=findViewById(R.id.btn_input_rule);
        btnScheduleViewer=findViewById(R.id.btn_schedule_viewer);
        btnAboutUs=findViewById(R.id.btn_about_us);
    }
    private void initListeners(){
        initClickOnListeners();
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
            Intent intent=new Intent(this,ActivityFillCourses.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.btn_input_rule){
            Intent intent=new Intent(this, ActivityShowRule.class);
            startActivity(intent);
        }

    }
}

package javaherian.yousef.entekhabvahed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




import heidari.mohammad.entekhabvahed.ActivityShowRule;
import heidari.mohammad.entekhabvahed.ActivityShowSchedule;
import heidari.mohammad.entekhabvahed.MusicManager;
import hoosmand.DatabaseModified;

public class ActivityMenu extends AppCompatActivity implements View.OnClickListener {
    private Button btnInputCourse;
    private Button btnInputRule;
    private Button btnScheduleViewer;
    private Button btnAboutUs;
    public ActivityMenu activity;
    private Intent svc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        initListeners();
        svc=new Intent(this, MusicManager.class);
        startService(svc);
        /**
         * creating the global database
         * these lines may change in future
         */
        Global.db = new DatabaseModified(this);
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
            Intent intent=new Intent(this,ActivityViewCourses.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.btn_input_rule){
            Intent intent=new Intent(this, ActivityShowRule.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.btn_schedule_viewer){
            Intent intent=new Intent(this, ActivityShowSchedule.class);
            startActivity(intent);
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //we should save the information in onDestroy function


    }
    @Override
    protected void onPause(){
        if (this.isFinishing()){ //basically BACK was pressed from this activity
            stopService(svc);

        }
        super.onPause();
    }
}

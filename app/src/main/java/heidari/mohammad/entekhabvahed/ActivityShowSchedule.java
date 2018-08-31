package heidari.mohammad.entekhabvahed;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import javaherian.yousef.entekhabvahed.R;

import static javaherian.yousef.entekhabvahed.Global.i;
import static javaherian.yousef.entekhabvahed.Global.mainProcess;

public class ActivityShowSchedule extends Activity implements View.OnClickListener {
    FloatingActionButton before;
    FloatingActionButton next;
    TextView scoreTextView;

    ArrayList<FragmentHomeCourse> a = new ArrayList<>();
    ArrayList<ArrayList<FragmentHomeCourse>> schedule = new ArrayList<>();
    ArrayList<FragmentHomeTable> day = new ArrayList<>();;
    ArrayList<String> day1 = new ArrayList<>();


    ModelScheduleVersion1 sch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_show_schedule);
        sch = mainProcess.getSchedule(i);
        findView();
        initArrayDay();
        initTableDay();
        initFragmentCourseHome();
        initTableCourse();
        initListener();
    }
    private void findView(){
        next = findViewById(R.id.next);
        before = findViewById(R.id.before);
        scoreTextView=findViewById(R.id.schedule_score_text_view);
        scoreTextView.setText("sch Number : "+sch.id+"  sch Total Score : "+sch.score);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.before){
            if(i==0){
                i=mainProcess.scheduleSize()-1;
                Intent intent = new Intent(this, ActivityShowSchedule.class);
                intent.putExtra("activity_id",i);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                startActivity(intent);
            }
            else {
                i--;
                Intent intent = new Intent(this, ActivityShowSchedule.class);
                intent.putExtra("activity_id",i);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                startActivity(intent);
            }
        }
        else if(view.getId()==R.id.next){
            if(i==mainProcess.scheduleSize()-1){
                i=0;
                Intent intent = new Intent(this, ActivityShowSchedule.class);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                startActivity(intent);
            }
            else{
                i++;
                Intent intent = new Intent(this, ActivityShowSchedule.class);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                startActivity(intent);
            }
        }
    }

    void initArrayDay(){
        day1.add("شنبه");
        day1.add("یک شنبه");
        day1.add("دوشنبه");
        day1.add("سه شنبه");
        day1.add("چهارشنبه");
        day1.add("پنچ شنبه");

    }
    private void initTableDay(){

        day.add((FragmentHomeTable)getFragmentManager().findFragmentById(R.id.fragment11));
        day.add((FragmentHomeTable)getFragmentManager().findFragmentById(R.id.fragment21));
        day.add((FragmentHomeTable)getFragmentManager().findFragmentById(R.id.fragment31));
        day.add((FragmentHomeTable)getFragmentManager().findFragmentById(R.id.fragment41));
        day.add((FragmentHomeTable)getFragmentManager().findFragmentById(R.id.fragment51));
        day.add((FragmentHomeTable)getFragmentManager().findFragmentById(R.id.fragment61));
        for(int i=0;i<6;i++){
            day.get(i).textView.setText(day1.get(i));
        }


    }
    private void initFragmentCourseHome(){
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment12));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment13));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment14));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment15));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment16));
        schedule.add((ArrayList<FragmentHomeCourse>) a.clone());
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment22));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment23));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment24));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment25));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment26));
        schedule.add((ArrayList<FragmentHomeCourse>) a.clone());
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment32));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment33));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment34));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment35));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment36));
        schedule.add((ArrayList<FragmentHomeCourse>) a.clone());
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment42));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment43));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment44));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment45));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment46));
        schedule.add((ArrayList<FragmentHomeCourse>) a.clone());
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment52));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment53));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment54));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment55));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment56));
        schedule.add((ArrayList<FragmentHomeCourse>) a.clone());
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment62));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment63));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment64));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment65));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment66));
        schedule.add((ArrayList<FragmentHomeCourse>) a.clone());
        a.clear();






    }
    private void initTableCourse(){
        //for every day
        int j=0;
        for(int i = 1 ;i<=6 ; i++) {
            j=0;
            //for every class time
            for (ModelCourseVersion1 item : sch.schedule.get(i)) {
                schedule.get(i-1).get(j).teacher_name.setText(item.teacherName);
                schedule.get(i-1).get(j).course_name.setText(item.courseName);
                schedule.get(i-1).get(j).course_time.setText(item.timing);
                j++;
            }
        }
    }
    void initListener(){
        next.setOnClickListener(this);
        before.setOnClickListener(this);
    }
}
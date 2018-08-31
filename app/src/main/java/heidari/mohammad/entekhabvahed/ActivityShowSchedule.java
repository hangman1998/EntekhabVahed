package heidari.mohammad.entekhabvahed;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javaherian.yousef.entekhabvahed.ModelGroup;
import javaherian.yousef.entekhabvahed.ModelSchedule;
import javaherian.yousef.entekhabvahed.R;

import static javaherian.yousef.entekhabvahed.Global.db;
import static javaherian.yousef.entekhabvahed.Global.i;
import static javaherian.yousef.entekhabvahed.Global.mainProcess;

public class ActivityShowSchedule extends Activity implements View.OnClickListener {
    Button before;
    Button next;
    FragmentHomeCourse fragmentHomeCourse;
    FragmentHomeTable fragmentHomeTable;
    ArrayList<FragmentHomeCourse> a = new ArrayList<>();
    ArrayList<ArrayList<FragmentHomeCourse>> schedule = new ArrayList<>();
    ArrayList<FragmentHomeTable> day = new ArrayList<>();;
    ArrayList<String> day1 = new ArrayList<>();
    ModelScheduleVersion1 modelScheduleVersion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_show_schedule);
//        //Intent intent=new Intent(this, ActivityShowSchedule.class);
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//        startActivity(intent);
        //modelSchedule = db.read
        modelScheduleVersion1 = mainProcess.getSchedule(i);
        findView();
       // makeTable();
        //initView(modelSchedules.get(i));
        initArrayDay();
        initTableDay();
        initTableCourse();
        initFragmentCourseHome();
        initListener();



    }
    private void findView(){
        next = findViewById(R.id.next);
        before = findViewById(R.id.before);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.before){
            if(i==1){
                Toast.makeText(ActivityShowSchedule.this,"you cant go to left",Toast.LENGTH_SHORT).show();
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
            if(i==mainProcess.scheduleSize()){
                Toast.makeText(ActivityShowSchedule.this,"you cant go to Right",Toast.LENGTH_SHORT).show();

            }
            else{
                i++;
                Intent intent = new Intent(this, ActivityShowSchedule.class);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                startActivity(intent);

            }
        }
    }
//    public void makeTable(){
//        for(int i=0;i<6;i++) {
//            tableRowa = new TableRow(this);
//            for(int j=0;j<5;j++) {
//                tableRowa.addView(fragmentHomeTable);
//            }
//            tableLayout.addView(tableRowa);
//        }

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
        schedule.add(a);
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment22));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment23));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment24));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment25));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment26));
        schedule.add(a);
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment32));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment33));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment34));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment35));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment36));
        schedule.add(a);
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment44));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment43));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment44));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment45));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment46));
        schedule.add(a);
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment55));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment53));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment54));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment55));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment56));
        schedule.add(a);
        a.clear();
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment66));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment63));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment64));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment65));
        a.add((FragmentHomeCourse) getFragmentManager().findFragmentById(R.id.fragment66));
        schedule.add(a);
        a.clear();






    }
    private void initTableCourse(){
        int numeber = modelScheduleVersion1.schedule.get(ModelGroup.SATURDAY).size();
        for(int j=0;j<6;j++) {
            for (int i = 0; i < numeber; i++) {
                String coursename = modelScheduleVersion1.schedule.get(j+1).get(i).courseName;
                String teachername = modelScheduleVersion1.schedule.get(j+1).get(i).courseName;
                String time = modelScheduleVersion1.schedule.get(j+1).get(i).timing;
                schedule.get(j).get(i).teacher_name.setText(teachername);
                schedule.get(j).get(i).course_name.setText(time);
                schedule.get(j).get(i).course_name.setText(teachername);
            }
        }
    }
    void initListener(){
        next.setOnClickListener(this);
        before.setOnClickListener(this);
    }
}





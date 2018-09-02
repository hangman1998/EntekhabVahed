package heidari.mohammad.entekhabvahed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javaherian.yousef.entekhabvahed.ActivityMenu;
import javaherian.yousef.entekhabvahed.R;

import static javaherian.yousef.entekhabvahed.Global.i;
import static javaherian.yousef.entekhabvahed.Global.mainProcess;

public class ActivityShowSchedule extends Activity implements View.OnTouchListener{
    TextView scoreTextView;

    ArrayList<FragmentHomeCourse> a = new ArrayList<>();
    ArrayList<ArrayList<FragmentHomeCourse>> schedule = new ArrayList<>();
    ArrayList<FragmentHomeTable> day = new ArrayList<>();;
    ArrayList<String> day1 = new ArrayList<>();

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    GestureDetector gestureDetector;
    TableLayout tableLayout;


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
        initGestureListener();
    }
    private void findView(){
        scoreTextView=findViewById(R.id.schedule_score_text_view);
        scoreTextView.setText("sch Number : "+i+"  sch Total Score : "+sch.score);
        tableLayout = findViewById(R.id.table);
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
    private  void initGestureListener(){
        gestureDetector=new GestureDetector(this,new MyGestureDetector(this));
        tableLayout.setOnTouchListener(this);


    }

    /**
     *  protected void onPause(){
     if (this.isFinishing()){ //basically BACK was pressed from this activity
     Intent intent = new Intent(ActivityShowSchedule.this,ActivityMenu.class);
     startActivity(intent);

     }
     super.onPause();
     }
      */

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }
    class MyGestureDetector extends SimpleOnGestureListener {
        ActivityShowSchedule a;

        public MyGestureDetector(ActivityShowSchedule a) {
            this.a = a;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH){
                    return false;
                }
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    a.onSwipeLeft();


                }
                // left to right swipe
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    a.onSwipeRight();


                }
            } catch (Exception e) {

            }
            return false;
        }
    }
    private void  onSwipeLeft(){
        if(i==mainProcess.scheduleSize()-1) i=0;
        else i++;
        Intent intent = new Intent(this, ActivityShowSchedule.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }
    private void onSwipeRight(){
        if(i==0) i=mainProcess.scheduleSize()-1;
        else i--;
        Intent intent = new Intent(this, ActivityShowSchedule.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        finish();

    }
}
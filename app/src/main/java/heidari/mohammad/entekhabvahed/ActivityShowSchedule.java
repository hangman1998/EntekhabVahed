package heidari.mohammad.entekhabvahed;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import javaherian.yousef.entekhabvahed.R;

public class ActivityShowSchedule extends Activity {
    LinearLayout activity_sh;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_show_schedule);
//        //Intent intent=new Intent(this, ActivityShowSchedule.class);
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//        startActivity(intent);
        findView();



    }
    private void findView(){



    }
}

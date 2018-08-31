package heidari.mohammad.entekhabvahed;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javaherian.yousef.entekhabvahed.ModelSchedule;
import javaherian.yousef.entekhabvahed.R;

import static javaherian.yousef.entekhabvahed.Global.db;
import static javaherian.yousef.entekhabvahed.Global.i;

public class ActivityShowSchedule extends Activity implements View.OnClickListener {
    List<ModelSchedule> modelSchedules;
    TableLayout tableLayout;
    TableRow tableRowa;
    TableRow tableRowb;
    TableRow tableRowc;
    TableRow tableRowd;
    TableRow tableRowe;
    TableRow tableRowf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_show_schedule);
//        //Intent intent=new Intent(this, ActivityShowSchedule.class);
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//        startActivity(intent);
        //modelSchedule = db.read
        i=getIntent().getExtras().getInt("activity_id");
        findView();
        makeTable();
        initView(modelSchedules.get(i));



    }
    private void findView(){
        tableLayout = findViewById(R.id.table);



    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.before){
            if(i==0){
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
            if(i==modelSchedules.size()-1){
                Toast.makeText(ActivityShowSchedule.this,"you cant go to Right",Toast.LENGTH_SHORT).show();

            }
            else{
                i++;
                Intent intent = new Intent(this, ActivityShowSchedule.class);
                intent.putExtra("activity_id",i);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                startActivity(intent);

            }
        }
    }
    public void makeTable(){
        for(int i=0;i<5;i++) {
            tableRowa = new TableRow();
            tableLayout.addView(tableRowa);
        }





    }
    private void initView(ModelSchedule modelSchedule){

    }
}

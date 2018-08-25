package heidari.mohammad.entekhabvahed;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import javaherian.yousef.entekhabvahed.R;

public class ActivityEditRule extends AppCompatActivity implements View.OnClickListener  {
    Spinner spin1;
    TextView name;
    TextView teacher;
    TextView course;
    TextView day;
    EditText name1;
    EditText teacher1;
    EditText course1;
    EditText start_time;
    EditText finish_time;

    Spinner day1;
    Spinner relation_start_time;
    Spinner relation_finish_time;
    CheckBox checkBox;
    CheckBox checkBox1;
    int id=0;
    int hour;
    int minute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityeditrule);
        findView();
        //we should initial  view of
        String i= getIntent().getExtras().getString("activity_id");
        Toast.makeText(ActivityEditRule.this, i, Toast.LENGTH_SHORT).show();
        start_time.setOnClickListener(this);
        finish_time.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    start_time.setEnabled(true);
                    start_time.setFocusable(true);
                    start_time.setInputType(InputType.TYPE_CLASS_TEXT);
                    relation_start_time.setEnabled(true);
                    relation_start_time.setFocusableInTouchMode(true);
                }
                else {
                    start_time.setEnabled(false);
                    start_time.setFocusable(false);
                    start_time.setInputType(InputType.TYPE_NULL);
                    relation_start_time.setEnabled(false);
                    relation_start_time.setFocusableInTouchMode(false);
                }
            }
        });
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    finish_time.setEnabled(true);
                    finish_time.setFocusable(true);
                    finish_time.setInputType(InputType.TYPE_NULL);
                    relation_finish_time.setEnabled(true);
                    relation_finish_time.setFocusableInTouchMode(true);
                }
                else {
                    finish_time.setEnabled(false);
                    finish_time.setFocusable(false);
                    finish_time.setInputType(InputType.TYPE_NULL);
                    relation_finish_time.setEnabled(false);
                    relation_finish_time.setFocusableInTouchMode(false);
                }
            }
        });

    }
    private TimePickerDialog.OnTimeSetListener time_set = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            hour = h;
            minute = m;
            if(m<10) {
                start_time.setText((h + ":0" + m));
            }
            else{
                start_time.setText((h + ":" + m));
            }
        }
    };
    private TimePickerDialog.OnTimeSetListener time_set_1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            hour = h;
            minute = m;
            if(m<10) {
                finish_time.setText((h + ":0" + m));
            }
            else{
                finish_time.setText((h + ":" + m));
            }
        }
    };


    private void findView(){
        //day=(TextView)findViewById(R.id.dTV);
        day1=(Spinner)findViewById(R.id.dSP);
        relation_start_time=findViewById(R.id.relation_start_time);
        relation_finish_time=findViewById(R.id.relation_finish_time);
        start_time=findViewById(R.id.start_time);
        finish_time=findViewById(R.id.finish_time);
        checkBox = findViewById(R.id.check_box1);
        checkBox1 =findViewById(R.id.check_box2);
        name1=(EditText)findViewById(R.id.nET);
        course1=(EditText)findViewById(R.id.cET);
        teacher1=(EditText)findViewById(R.id.tET);
        initView();
        start_time.setText("please click here");
        finish_time.setText("please click here");



    }
    private void initView(){
        start_time.setEnabled(false);
        start_time.setFocusable(false);
        start_time.setInputType(InputType.TYPE_NULL);
        relation_start_time.setEnabled(false);
        relation_start_time.setFocusableInTouchMode(false);
        finish_time.setEnabled(false);
        finish_time.setFocusable(false);
        finish_time.setInputType(InputType.TYPE_NULL);
        relation_finish_time.setEnabled(false);
        relation_finish_time.setFocusableInTouchMode(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.planets_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        day1.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.relation_start,android.R.layout.simple_dropdown_item_1line);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        relation_start_time.setAdapter(adapter1);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.relation_start,android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        relation_finish_time.setAdapter(adapter2);
    };
    @Override
    public void onClick(View view) {
        if (view == null)
            return;
        if (view.getId()==R.id.start_time){
            Dialog dialog= new TimePickerDialog(this,time_set,hour,minute,false);
            dialog.show();
        }
        else if(view.getId()==R.id.finish_time){
            Dialog dialog= new TimePickerDialog(this,time_set_1,hour,minute,false);
            dialog.show();
        }


    }

}

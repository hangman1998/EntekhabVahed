package heidari.mohammad.entekhabvahed;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javaherian.yousef.entekhabvahed.ModelCourse;
import javaherian.yousef.entekhabvahed.ModelRule;
import javaherian.yousef.entekhabvahed.R;

import static javaherian.yousef.entekhabvahed.Global.db;

public class ActivityEditRule extends AppCompatActivity implements View.OnClickListener  {
    //Spinner spin1;
    public static final int RESULTED_IN_CANCEL = 0;
    public static final int RESULTED_IN_OK = 1;

    DialogTimePickerStart dialogTimePickerStart;
    DialogTimePickerFinish dialogTimePickerFinish;
    Intent intent;
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

    Button ok;
    Button cansel;

    private SeekBar score1;

    int id=0;
    int hour;
    int minute;
    private String Action;
    ModelRule modelRule;
    TextView score;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityeditrule);
        findView();
        initView();
        initListener();
        initDatabase();

        //we should initial  view of
//        String i= getIntent().getExtras().getString("activity_id");
//        Toast.makeText(ActivityEditRule.this, i, Toast.LENGTH_SHORT).show();



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
        score1=(SeekBar)findViewById(R.id.score_seekbar);
        score=(TextView)findViewById(R.id.score_textview);
        ok=(Button)findViewById(R.id.ok);
        cansel=(Button)findViewById(R.id.cansel);


//        start_time.setText("please click here");
//        finish_time.setText("please click here");



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
        //seting spinner adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.planets_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        day1.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.relation_start,android.R.layout.simple_dropdown_item_1line);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        relation_start_time.setAdapter(adapter1);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.relation_start,android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        relation_finish_time.setAdapter(adapter2);
        //seting Text Edit
        start_time.setText("please click here");
        finish_time.setText("please click here");
        score.setText(""+0);
        //seting seekbar
        score1.setMax(100);
    };
    private void initListener(){
        start_time.setOnClickListener(this);
        finish_time.setOnClickListener(this);
        ok.setOnClickListener(this);
        cansel.setOnClickListener(this);
        score1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub      {
                score.setText("" + progress);
            }
        });
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
    private void initDatabase(){
        Bundle extras = getIntent().getExtras();
        Action = getIntent().getAction();
        if(Action=="CREATE_RULE"){
             modelRule=new ModelRule();
        }
        else if (Action=="EDIT_RULE"){
             name=extras.getString("Activity_id");
             List<ModelRule> listRule=db.readRule();
            for (int i=0 ;i <listRule.size();i++){
                if(listRule.get(i).getName()==name){
                    assigneView(listRule.get(i));
                    modelRule=listRule.get(i);
                    break;
                }
            }

        }
    }
    @Override
    public void onClick(View view) {
        if (view == null)
            return;
        if (view.getId()==R.id.start_time){
            //Dialog dialog= new TimePickerDialog(this,time_set,hour,minute,false);
           // dialog.show();
            dialogTimePickerStart = new DialogTimePickerStart(ActivityEditRule.this);
            dialogTimePickerStart.show();
        }
        else if(view.getId()==R.id.finish_time){
           // Dialog dialog= new TimePickerDialog(this,time_set_1,hour,minute,false);
           // dialog.show();
            dialogTimePickerFinish = new DialogTimePickerFinish(ActivityEditRule.this);
            dialogTimePickerFinish.show();
        }
        else if(view.getId()==R.id.ok){
            if (name1.getText().toString().isEmpty()){
                Toast.makeText(this, "name field can't be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(teacher1.getText().toString().isEmpty()){
                Toast.makeText(this, "teacher name field can't be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(course1.getText().toString().isEmpty()){
                Toast.makeText(this, "course name field can't be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            ArrayList<ModelRule> rules = db.readRule();
            ArrayList<ModelCourse> courses = db.readCourses();
            String newteacher= teacher1.getText().toString();
            String newcourse = course1.getText().toString();
            String newname = name1.getText().toString();
            int start_time;
            int finish_time;
            int newday ;
            int sTR;
            int fTR;
            switch (day1.getSelectedItem().toString()){
                case "همه روز ها":
                    newday=0;
                    break;
                case "شنبه"  :
                    newday=1;

                case "یک شنبه":
                    newday=2;
                    break;
                case "دو شنبه":
                    newday=3;
                    break;
                case "سه شنبه":
                    newday=4;
                    break;
                case "چهار شنبه":
                    newday=5;
                    break;
                default:
                    newday=0;

            }
            if(checkBox.isChecked()==true) {
                start_time = dialogTimePickerStart.getTimePickerH() * 60 + dialogTimePickerStart.getTimePickerM();
                if(relation_start_time.getSelectedItem()=="قبل از"){
                    sTR=0;
                }
                else{
                    sTR=1;
                }
            }
            else{
                start_time=-1;
                sTR=-1;
            }
            if(checkBox1.isChecked()==true) {
                finish_time = dialogTimePickerFinish.getTimePickerH() * 60 + dialogTimePickerFinish.getTimePickerM();
                if(relation_finish_time.getSelectedItem()=="قبل از"){
                    fTR=0;
                }
                else {
                    fTR = 1;
                }
            }
            else{
                finish_time=-1;
                fTR=-1;
            }

            int score = score1.getProgress();

            int k=0;
            if (Action == "CREATE_RULE"){
                for (int i=0;i<rules.size();i++){
                    if (rules.get(i).getName() == newname){
                        Toast.makeText(this, "Error:Duplicate name", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                for (int i=0;i<courses.size();i++){
                    if (courses.get(i).getName() == newcourse){
                        k=1;
                    }

                }

                if(k==1){
                    Toast.makeText(this, "Error:Coursename dont exsist in your course", Toast.LENGTH_SHORT).show();
                    return;
                }
                modelRule.setDay(newday);
                modelRule.setName(newname);
                modelRule.setFinishTime(finish_time);
                modelRule.setStartTime(start_time);
                modelRule.setCourse(newcourse);
                modelRule.setTeacher(newteacher);
                modelRule.setStartTimeRelation(sTR);
                modelRule.setFinishTimeRelation(fTR);
                modelRule.setScore(score);
                db.addRule(modelRule);
                Toast.makeText(this, "Error:add EDited", Toast.LENGTH_SHORT).show();
            }
            else if (Action == "EDIT_RULE"){
                for (int i=0;i<rules.size();i++){
                    if (rules.get(i).getName() == newname){
                        Toast.makeText(this, "Error:Duplicate name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(courses.get(i).getName() == newcourse){
                        k=1;

                    }
                }
                if(k==0){
                    Toast.makeText(this, "Error:Coursename dont exsist in your course", Toast.LENGTH_SHORT).show();
                    return;
                }
                modelRule.setDay(newday);
                modelRule.setName(newname);
                modelRule.setFinishTime(finish_time);
                modelRule.setStartTime(start_time);
                modelRule.setCourse(newcourse);
                modelRule.setTeacher(newteacher);
                modelRule.setStartTimeRelation(sTR);
                modelRule.setFinishTimeRelation(fTR);
                modelRule.setScore(score);
                db.deleteRule(name);
                db.addRule(modelRule);
                Toast.makeText(this, "Error:rule EDited", Toast.LENGTH_SHORT).show();
            }
            intent = new Intent(this,ActivityShowRule.class);
            setResult(RESULTED_IN_OK,intent);
            finish();

        }

        else if(view.getId()==R.id.cansel){
            intent = new Intent(this,ActivityShowRule.class);
            setResult(RESULTED_IN_CANCEL,intent);
            finish();
        }
    }
    private void assigneView(ModelRule rule){

        day1.setSelection(rule.getDay());
        relation_start_time.setSelection(rule.getStartTimeRelation());
        relation_finish_time.setSelection(rule.getFinishTimeRelation());
        name1.setText(rule.getName());
        teacher1.setText(rule.getTeacher());
        course1.setText(rule.getCourse());
        //i will assigne starttime and
        score.setText(rule.getScore());
        score1.setProgress(rule.getScore());
        start_time.setText(rule.getStartTime()/60+":"+rule.getStartTime()%60);
        finish_time.setText(rule.getFinishTime()/60+":"+rule.getFinishTime()%60);
        dialogTimePickerStart.setTimePicker(rule.getStartTime());
        dialogTimePickerFinish.setTimePicker(rule.getFinishTime());
    }
    public void setEditextFinish(int h, int m){
        if(m<10) {
            finish_time.setText((h + ":0" + m));
        }
        else{
            finish_time.setText((h + ":" + m));
        }
    }
    public void setEditextStart(int h, int m){
        if(m<10) {
            start_time.setText((h + ":0" + m));
        }
        else{
            start_time.setText((h + ":" + m));
        }
    }
}

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
        score1.setMax(10);
        dialogTimePickerStart=new DialogTimePickerStart(this);
        dialogTimePickerFinish= new DialogTimePickerFinish(this);
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
                    dialogTimePickerStart = new DialogTimePickerStart(ActivityEditRule.this);


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
                    dialogTimePickerFinish = new DialogTimePickerFinish(ActivityEditRule.this);

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
                if(listRule.get(i).getName().equals(name)){
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
            dialogTimePickerStart.show();
        }
        else if(view.getId()==R.id.finish_time){
           // Dialog dialog= new TimePickerDialog(this,time_set_1,hour,minute,false);
           // dialog.show();
            dialogTimePickerFinish.show();
        }
        else if(view.getId()==R.id.ok){
            if (name1.getText().toString().isEmpty()){
                Toast.makeText(this, "name field can't be empty!", Toast.LENGTH_SHORT).show();
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
            int starttime;
            int finishtime;
            int newday ;
            int sTR;
            int fTR;
            newday=day1.getSelectedItemPosition();
            if(!checkBox.isChecked()&& dialogTimePickerStart.getTimePickerH()!= -1){
                Toast.makeText(this, "your Start time is not changed", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "if you want to change Start time you should check checkbox;", Toast.LENGTH_SHORT).show();
            }
            if(checkBox.isChecked()&& dialogTimePickerStart.getTimePickerH() != -1) {
                starttime = dialogTimePickerStart.getTimePickerH() * 60 + dialogTimePickerStart.getTimePickerM();
                if(relation_start_time.getSelectedItemPosition()==0){
                    sTR=0;
                }
                else{
                    sTR=1;
                }
            }
            else if(Action.equals("EDIT_RULE")){
                starttime=modelRule.getStartTime();
                sTR=modelRule.getStartTimeRelation();
            }
            else{
                starttime=0;
                sTR=0;
            }
            if(!checkBox.isChecked()&& dialogTimePickerFinish.getTimePickerH()!= -1){
                Toast.makeText(this, "your Finish time is not changed", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "if you want to change Finish time you should check checkbox;", Toast.LENGTH_SHORT).show();
            }
            if(checkBox1.isChecked() && dialogTimePickerFinish.getTimePickerH()!= -1) {
                finishtime = dialogTimePickerFinish.getTimePickerH() * 60 + dialogTimePickerFinish.getTimePickerM();
                if(relation_finish_time.getSelectedItemPosition()==0){
                    fTR=0;
                }
                else {
                    fTR = 1;
                }
            }
            else if(Action.equals("EDIT_RULE")){
                finishtime=modelRule.getFinishTime();
                fTR=modelRule.getFinishTimeRelation();
            }
            else{
                finishtime=0;
                fTR=0;
            }

            int score = score1.getProgress();

            int k=0;
            if (Action.equals("CREATE_RULE")){
                for (int i=0;i<rules.size();i++) {
                    if (rules.get(i).getName().equals(newname)) {
                                Toast.makeText(this, "Error:Duplicate name", Toast.LENGTH_SHORT).show();
                                return;
                    }
                }

                for (int i=0;i<courses.size();i++){
                    if (courses.get(i).getName().equals(newcourse)){
                        k=1;
                    }

                }

                if(k==0){
                    Toast.makeText(this, "Error:Coursename dont exsist in your course", Toast.LENGTH_SHORT).show();
                    return;
                }
                modelRule.setDay(newday);
                modelRule.setName(newname);
                modelRule.setFinishTime(finishtime);
                modelRule.setStartTime(starttime);
                modelRule.setCourse(newcourse);
                modelRule.setTeacher(newteacher);
                modelRule.setStartTimeRelation(sTR);
                modelRule.setFinishTimeRelation(fTR);
                modelRule.setScore(score);
                db.addRule(modelRule);
            }
            else if (Action.equals("EDIT_RULE")){
                if(!newname.equals(name)){
                    for (int i=0;i<rules.size();i++) {
                        if (rules.get(i).getName().equals(newname)) {
                            Toast.makeText(this, "Error:Duplicate name", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                for (int i=0;i<courses.size();i++){
                    if (courses.get(i).getName().equals(newcourse)){
                        k=1;
                    }

                }
                if(k==0){
                    Toast.makeText(this, "Error:Coursename dont exsist in your course", Toast.LENGTH_SHORT).show();
                    return;
                }
                modelRule.setDay(newday);
                modelRule.setName(newname);
                modelRule.setFinishTime(finishtime);
                modelRule.setStartTime(starttime);
                modelRule.setCourse(newcourse);
                modelRule.setTeacher(newteacher);
                modelRule.setStartTimeRelation(sTR);
                modelRule.setFinishTimeRelation(fTR);
                modelRule.setScore(score);
                db.deleteRule(name);
                db.addRule(modelRule);
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
        score.setText(""+rule.getScore());
        score1.setProgress(rule.getScore());
        if(rule.getStartTime()==0){
            start_time.setText("please click here");
        }
        else {
            if((rule.getStartTime()%60)<10){
                start_time.setText(rule.getStartTime() / 60 + ":0" + rule.getStartTime() % 60);
            }
            else {
                start_time.setText(rule.getStartTime() / 60 + ":" + rule.getStartTime() % 60);
            }

        }
        if(rule.getFinishTime()==0){
            finish_time.setText("please click here");
        }
        else {
            if((rule.getFinishTime()%60)<10){
                finish_time.setText(rule.getFinishTime() / 60 + ":0" + rule.getFinishTime() % 60);
            }
            else {
                finish_time.setText(rule.getFinishTime() / 60 + ":" + rule.getFinishTime() % 60);
            }
        }
       // dialogTimePickerFinish = new DialogTimePickerFinish(ActivityEditRule.this);
       // dialogTimePickerStart = new DialogTimePickerStart(ActivityEditRule.this);

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

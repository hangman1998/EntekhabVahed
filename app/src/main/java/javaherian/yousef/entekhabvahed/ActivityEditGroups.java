package javaherian.yousef.entekhabvahed;
/**
 * created by yousef for handling the editing an existing group or  creating a new group
 */

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityEditGroups extends AppCompatActivity implements View.OnClickListener {
    /**
     * result codes to send for preceding activity
     */
    public static final int RESULTED_IN_OK = 0;
    public static final int RESULTED_IN_CANCEL = 1;
    /**
     * actions which are coming from previous activity(fill courses) are
     * 1.CREATE_GROUP(sends nothing receives a model group)
     * 2.EDIT_GROUP (sends a position and also a group and receives a group and the same position)
     */
    private EditText idEditText, teacherEditText;
    private Button okBtn, cancelBtn;
    private CustomTimePicker timePicker1, timePicker2, timePicker3;


    private int position;
    private String Action;
    private ModelGroup mGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_groups);
        findViews();
        initDatabase();
        initListeners();
    }

    private void findViews() {
        idEditText = findViewById(R.id.groups_edit_id_edit_text);
        teacherEditText = findViewById(R.id.groups_edit_teacher_edit_text);
        okBtn = findViewById(R.id.groups_edit_ok_button);
        cancelBtn = findViewById(R.id.groups_edit_cancel_button);
        timePicker1 = (CustomTimePicker) getFragmentManager().findFragmentById(R.id.groups_edit_fragment1);
        timePicker2 = (CustomTimePicker) getFragmentManager().findFragmentById(R.id.groups_edit_fragment2);
        timePicker3 = (CustomTimePicker) getFragmentManager().findFragmentById(R.id.groups_edit_fragment3);

    }

    private void initDatabase() {

        Bundle extras = getIntent().getExtras();
        Action = getIntent().getAction();

        if (Action == "CREATE_GROUP") {
            mGroup = new ModelGroup();
            position = -1;
        } else if (Action == "EDIT_GROUP") {
            /**
             *
             * we are assuming that we have a position
             */
            position = extras.getInt("position", -1);
            mGroup = (ModelGroup) extras.getSerializable("model group");

            idEditText.setText(mGroup.getGroupId());
            teacherEditText.setText(mGroup.getTeacherName());
            /**
             * we now need to set the values of the three fragments
             */
            timePicker1.spinner.setSelection(mGroup.getDay1());
            timePicker2.spinner.setSelection(mGroup.getDay2());
            timePicker3.spinner.setSelection(mGroup.getDay3());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker1.startTimePicker.setHour(mGroup.getStartTime1()/60);
                timePicker1.startTimePicker.setMinute(mGroup.getStartTime1()%60);
                timePicker1.finishTimePicker.setHour(mGroup.getFinishTime1()/60);
                timePicker1.finishTimePicker.setMinute(mGroup.getFinishTime1()%60);

                timePicker2.startTimePicker.setHour(mGroup.getStartTime2()/60);
                timePicker2.startTimePicker.setMinute(mGroup.getStartTime2()%60);
                timePicker2.finishTimePicker.setHour(mGroup.getFinishTime2()/60);
                timePicker2.finishTimePicker.setMinute(mGroup.getFinishTime2()%60);

                timePicker3.startTimePicker.setHour(mGroup.getStartTime3()/60);
                timePicker3.startTimePicker.setMinute(mGroup.getStartTime3()%60);
                timePicker3.finishTimePicker.setHour(mGroup.getFinishTime3()/60);
                timePicker3.finishTimePicker.setMinute(mGroup.getFinishTime3()%60);
            } else {
                timePicker1.startTimePicker.setCurrentHour(mGroup.getStartTime1()/60);
                timePicker1.startTimePicker.setCurrentMinute(mGroup.getStartTime1()%60);
                timePicker1.finishTimePicker.setCurrentHour(mGroup.getFinishTime1()/60);
                timePicker1.finishTimePicker.setCurrentMinute(mGroup.getFinishTime1()%60);

                timePicker2.startTimePicker.setCurrentHour(mGroup.getStartTime2()/60);
                timePicker2.startTimePicker.setCurrentMinute(mGroup.getStartTime2()%60);
                timePicker2.finishTimePicker.setCurrentHour(mGroup.getFinishTime2()/60);
                timePicker2.finishTimePicker.setCurrentMinute(mGroup.getFinishTime2()%60);

                timePicker3.startTimePicker.setCurrentHour(mGroup.getStartTime3()/60);
                timePicker3.startTimePicker.setCurrentMinute(mGroup.getStartTime3()%60);
                timePicker3.finishTimePicker.setCurrentHour(mGroup.getFinishTime3()/60);
                timePicker3.finishTimePicker.setCurrentMinute(mGroup.getFinishTime3()%60);
            }
        }
        else{
            finish();
        }
    }

    private void initListeners() {
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == null) return;
        //cancel Button
        if (view.getId() == R.id.groups_edit_cancel_button) {
            Intent intent = new Intent(this, ActivityFillCourses.class);
            setResult(RESULTED_IN_CANCEL, intent);
            finish();

        } else if (view.getId() == R.id.groups_edit_ok_button) {
            if (checkForValidity() == false){return;};
            updateMGroup();
            Intent intent = new Intent(this,ActivityFillCourses.class);
            intent.putExtra("position",position);
            intent.putExtra("model group",mGroup);
            setResult(RESULTED_IN_OK,intent);
            finish();}
    }

    private void updateMGroup() {
        mGroup.setGroupId(Integer.valueOf(idEditText.getText().toString()));
        mGroup.setTeacherName(teacherEditText.getText().toString());
        int st1,st2,st3,ft1,ft2,ft3;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            st1=timePicker1.startTimePicker.getHour()*60 + timePicker1.startTimePicker.getMinute();
            st2=timePicker2.startTimePicker.getHour()*60 + timePicker2.startTimePicker.getMinute();
            st3=timePicker3.startTimePicker.getHour()*60 + timePicker3.startTimePicker.getMinute();

            ft1=timePicker1.finishTimePicker.getHour()*60 + timePicker1.finishTimePicker.getMinute();
            ft2=timePicker2.finishTimePicker.getHour()*60 + timePicker2.finishTimePicker.getMinute();
            ft3=timePicker3.finishTimePicker.getHour()*60 + timePicker3.finishTimePicker.getMinute();
        } else {
            st1=timePicker1.startTimePicker.getCurrentHour()*60 + timePicker1.startTimePicker.getCurrentMinute();
            st2=timePicker2.startTimePicker.getCurrentHour()*60 + timePicker2.startTimePicker.getCurrentMinute();
            st3=timePicker3.startTimePicker.getCurrentHour()*60 + timePicker3.startTimePicker.getCurrentMinute();

            ft1=timePicker1.finishTimePicker.getCurrentHour()*60 + timePicker1.finishTimePicker.getCurrentMinute();
            ft2=timePicker2.finishTimePicker.getCurrentHour()*60 + timePicker2.finishTimePicker.getCurrentMinute();
            ft3=timePicker3.finishTimePicker.getCurrentHour()*60 + timePicker3.finishTimePicker.getCurrentMinute();
        }
        mGroup.setDay1(timePicker1.spinner.getSelectedItemPosition());
        mGroup.setDay2(timePicker2.spinner.getSelectedItemPosition());
        mGroup.setDay3(timePicker3.spinner.getSelectedItemPosition());

        mGroup.setStartTime1(st1);
        mGroup.setStartTime2(st2);
        mGroup.setStartTime3(st3);

        mGroup.setFinishTime1(ft1);
        mGroup.setFinishTime2(ft2);
        mGroup.setFinishTime3(ft3);

    }

    private boolean checkForValidity() {
        /**
         * in the future we should implement a check for overlapping timings
         * check for duplicate id will be made at activity  fill courses
         */
       int st1,st2,st3,ft1,ft2,ft3;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            st1=timePicker1.startTimePicker.getHour()*60 + timePicker1.startTimePicker.getMinute();
            st2=timePicker2.startTimePicker.getHour()*60 + timePicker2.startTimePicker.getMinute();
            st3=timePicker3.startTimePicker.getHour()*60 + timePicker3.startTimePicker.getMinute();

            ft1=timePicker1.finishTimePicker.getHour()*60 + timePicker1.finishTimePicker.getMinute();
            ft2=timePicker2.finishTimePicker.getHour()*60 + timePicker2.finishTimePicker.getMinute();
            ft3=timePicker3.finishTimePicker.getHour()*60 + timePicker3.finishTimePicker.getMinute();
        } else {
            st1=timePicker1.startTimePicker.getCurrentHour()*60 + timePicker1.startTimePicker.getCurrentMinute();
            st2=timePicker2.startTimePicker.getCurrentHour()*60 + timePicker2.startTimePicker.getCurrentMinute();
            st3=timePicker3.startTimePicker.getCurrentHour()*60 + timePicker3.startTimePicker.getCurrentMinute();

            ft1=timePicker1.finishTimePicker.getCurrentHour()*60 + timePicker1.finishTimePicker.getCurrentMinute();
            ft2=timePicker2.finishTimePicker.getCurrentHour()*60 + timePicker2.finishTimePicker.getCurrentMinute();
            ft3=timePicker3.finishTimePicker.getCurrentHour()*60 + timePicker3.finishTimePicker.getCurrentMinute();
        }
        // checking the emptiness
        if (teacherEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "teacher name can't be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (idEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "group id can't be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        //checking that finish time must be greater than start time
        if (timePicker1.spinner.getSelectedItemPosition() != ModelGroup.NULL){
            if (ft1<= st1){
                Toast.makeText(this, "incorrect first timing!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (timePicker2.spinner.getSelectedItemPosition() != ModelGroup.NULL){
            if (ft2<= st2){
                Toast.makeText(this, "incorrect second timing!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (timePicker3.spinner.getSelectedItemPosition() != ModelGroup.NULL){
            if (ft3<= st3){
                Toast.makeText(this, "incorrect third timing!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        // checking that we have at least a non empty timing
        if (timePicker3.spinner.getSelectedItemPosition() == ModelGroup.NULL &&
                timePicker2.spinner.getSelectedItemPosition() == ModelGroup.NULL
        && timePicker1.spinner.getSelectedItemPosition() == ModelGroup.NULL)
        {
            Toast.makeText(this, "all three timings can't be NULL !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
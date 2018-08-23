package javaherian.yousef.entekhabvahed;
/**
 * created by yousef for handling the editing an existing group or  creating a new group
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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
            /**
             * need to update the mgroup and also check for the validity of input
             */
            Intent intent = new Intent(this,ActivityFillCourses.class);
            intent.putExtra("position",position);
            intent.putExtra("model group",mGroup);
            setResult(RESULTED_IN_OK,intent);
            finish();}
    }
}
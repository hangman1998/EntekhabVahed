package javaherian.yousef.entekhabvahed;
/**
 * created by yousef
 * the main form for creating a new course
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityFillCourses extends AppCompatActivity implements View.OnClickListener {
    EditText editTextCourseName,editTextCourseId;
    Button okBtn,cancelBtn,addGroupsBtn;
    RecyclerView recyclerView;
    Bundle extras;
    ModelCourse mCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_courses);
        findViews();
        initRecycleView();
        initListeners();
        extras=getIntent().getExtras();

        if (extras.getBoolean("Create a new course",false) == true){
            /**
             * here we don't do anything
             * for now
             */
            mCourse = new ModelCourse();
        }
        else {
            /**
             * we are assuming here that we a have course id to work with
             */
            int courseId=extras.getInt("course id");
            ArrayList<ModelCourse> courses=Global.db.readCourses();
            /**
             * here we obtain the mCourse
             * need to change
             */
            for (int i=0 ;i <courses.size();i++)
            {
                if (courses.get(i).getId()==courseId) mCourse = courses.get(i);
            }

            editTextCourseId.setText(mCourse.getId());
            editTextCourseName.setText(mCourse.getName());
        }
        }
    private void findViews(){
        editTextCourseId=findViewById(R.id.edit_text_course_id);
        editTextCourseName=findViewById(R.id.edit_text_course_name);
        okBtn = findViewById(R.id.btn_fill_courses_ok);
        cancelBtn = findViewById(R.id.btn_fill_courses_cancel);
        addGroupsBtn = findViewById(R.id.btn_add_groups);
        recyclerView = findViewById(R.id.groups_recycler_view);
    }

    private void initRecycleView(){
        /**
         * for initializing the the recycler view
         * note that we have to do something about the timings
         */
        ArrayList<String> mIds = new ArrayList<>();
        ArrayList<String> mTeacherNames = new ArrayList<>();
        ArrayList<String> mTimings = new ArrayList<>();
        for (int i=0 ;i<mCourse.getGroups().size();i++)
        {
            mIds.add(String.valueOf(mCourse.getGroups().get(i).getGroupId()));
            mTeacherNames.add(mCourse.getGroups().get(i).getTeacherName());
            /**
             * timings
             */
            mTimings.add("test");
        }
         recyclerView.setAdapter(new GroupsRecyclerViewAdaptor(this,mIds,mTeacherNames,mTimings));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initListeners(){
        initClickOnListeners();
    }

    private void initClickOnListeners() {
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        addGroupsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == null)
            return;
        else if (view.getId() == R.id.btn_fill_courses_ok){
            /**
             * need SQL to complete this part
             */

        }
        else if (view.getId() == R.id.btn_fill_courses_cancel){
            /**
             * need SQL to complete this part
             * based on the extra content of the intent that we have received we need to
             * either delete  the newly created course in database or do nothing.
             */

        }
        else if (view.getId() == R.id.btn_add_groups){
            Toast.makeText(this, "no problem3", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ActivityEditGroups.class);
            intent.putExtra("Create a new group",true);
            startActivity(intent);

        }
    }
}

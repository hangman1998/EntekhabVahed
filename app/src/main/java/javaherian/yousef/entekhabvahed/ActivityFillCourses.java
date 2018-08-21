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

import static javaherian.yousef.entekhabvahed.Global.db;

public class ActivityFillCourses extends AppCompatActivity implements View.OnClickListener {
    /**
     * result codes to send for preceding activity
     */
    public static final int RESULTED_IN_CANCEL = 0;
    /**
     * request codes to send for the next activity
     */
    public static final int CREATE_GROUP = 0;
    EditText editTextCourseName,editTextCourseId;
    Button okBtn,cancelBtn,addGroupsBtn;
    RecyclerView recyclerView;

    String Action;
    ModelCourse mCourse;
    int courseId;
    ArrayList<String> mIds,mTeacherNames,mTimings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_courses);
        findViews();
        initDatabase();
        initRecycleView();
        initListeners();
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
         */
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

















        }
        else if (view.getId() == R.id.btn_fill_courses_cancel){

            Intent intent = new Intent(this,ActivityViewCourses.class);
            setResult(RESULTED_IN_CANCEL,intent);
            finish();
        }
        else if (view.getId() == R.id.btn_add_groups){
            Intent intent = new Intent(this,ActivityEditGroups.class);
            intent.setAction("CREATE_GROUP");
            startActivityForResult(intent,CREATE_GROUP);

        }
    }
    private void initDatabase(){

        Bundle extras = getIntent().getExtras();
        Action = getIntent().getAction();
        if (Action == "CREATE_COURSE"){
            mCourse = new ModelCourse();
        }
        else {
            /**
             * we are assuming here that we a have course id to work with
             */
            courseId=extras.getInt("course id");
            ArrayList<ModelCourse> courses= db.readCourses();
            /**
             * here we obtain the mCourse
             * the below code needs to change
             */
            for (int i=0 ;i <courses.size();i++)
            {
                if (courses.get(i).getId()==courseId) mCourse = courses.get(i);
            }
            editTextCourseId.setText(mCourse.getId());
            editTextCourseName.setText(mCourse.getName());
        }
        mIds = new ArrayList<>();
        mTeacherNames = new ArrayList<>();
        mTimings = new ArrayList<>();
        for (int i=0 ;i<mCourse.getGroups().size();i++)
        {
            mIds.add(String.valueOf(mCourse.getGroups().get(i).getGroupId()));
            mTeacherNames.add(mCourse.getGroups().get(i).getTeacherName());
            /**
             * timings
             */
            mTimings.add("test");
        }
    }
}

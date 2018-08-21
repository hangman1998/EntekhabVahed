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

public class ActivityFillCourses extends AppCompatActivity implements View.OnClickListener {
    EditText editTextCourseName,editTextCourseId;
    Button okBtn,cancelBtn,addGroupsBtn;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_courses);
        findViews();
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
         * need to have SQL database in order to complete this part!!
         */
        // recyclerView.setAdapter(new GroupsRecyclerViewAdaptor(this,));
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

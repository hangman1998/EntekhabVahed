package javaherian.yousef.entekhabvahed;
/**
 * created by yousef
 * main activity for viewing courses
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ActivityViewCourses extends AppCompatActivity implements View.OnClickListener {
    /**
     * request codes to send for the next activity
     */
    public static final int CREATE_COURSE=0;
    public static final int EDIT_COURSE =1;

    private FloatingActionButton addCourseBtn;
    private RecyclerView recyclerView;
    private CoursesRecyclerViewAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        findViews();
        initRecycleView();
        initListeners();
    }
    private void findViews(){
        addCourseBtn=findViewById(R.id.btn_add_course);
        recyclerView=findViewById(R.id.courses_recycler_view);
    }
    private void initRecycleView(){
        /**
         * for initializing the the recycler view
         */
        adaptor = new CoursesRecyclerViewAdaptor(this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initListeners() { addCourseBtn.setOnClickListener(this); }
    public void onClick(View view) {
        if (view == null) return;
        if (view.getId() == R.id.btn_add_course){
            Intent intent = new Intent(this,ActivityFillCourses.class);
            intent.setAction("CREATE_COURSE");
            startActivityForResult(intent,CREATE_COURSE);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CREATE_COURSE && resultCode == ActivityFillCourses.RESULTED_IN_CANCEL){
            /**
             * do absolutely nothing
             */
        }
        else if (requestCode == CREATE_COURSE && resultCode == ActivityFillCourses.RESULTED_IN_OK){
            adaptor.notifyItemAdded();
        }
        else if (requestCode == EDIT_COURSE && resultCode == ActivityFillCourses.RESULTED_IN_CANCEL){
            /**
             * do absolutely nothing
             */
        }
        else if (requestCode == EDIT_COURSE && resultCode == ActivityFillCourses.RESULTED_IN_OK){
            adaptor.notifyItemEdited();
        }
    }
}
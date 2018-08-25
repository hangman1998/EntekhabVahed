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
    public static final int RESULTED_IN_OK = 1;
    /**
     * request codes to send for the next activity
     */
    public static final int CREATE_GROUP = 0;
    public static final int EDIT_GROUP = 1;
    private EditText editTextCourseName,editTextCourseId;
    private Button okBtn,cancelBtn,addGroupsBtn;
    private RecyclerView recyclerView;
    private GroupsRecyclerViewAdaptor adaptor;

    private String Action;
    private ModelCourse mCourse;
    private int courseId;
    private ArrayList<String> mIds,mTeacherNames,mTimings;
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

    private void initDatabase(){

        Bundle extras = getIntent().getExtras();
        Action = getIntent().getAction();

        if (Action == "CREATE_COURSE"){
            mCourse = new ModelCourse();
        }
        else if (Action == "EDIT_COURSE") {
            /**
             * we are assuming here that we a have course id to work with
             */
            courseId=extras.getInt("course id");
            ArrayList<ModelCourse> courses= db.readCourses();
            /**
             * here we obtain the mCourse
             * the below code needs to change in future
             */
            for (int i=0 ;i <courses.size();i++)
            {
                if (courses.get(i).getId()==courseId) mCourse = courses.get(i);
            }
            editTextCourseId.setText(String.valueOf(mCourse.getId()));
            editTextCourseName.setText(mCourse.getName());
        }
        mIds = new ArrayList<>();
        mTeacherNames = new ArrayList<>();
        mTimings = new ArrayList<>();
        for (int i=0 ;i<mCourse.getGroups().size();i++)
        {
            mIds.add(String.valueOf(mCourse.getGroups().get(i).getGroupId()));
            mTeacherNames.add(mCourse.getGroups().get(i).getTeacherName());
            mTimings.add(mCourse.getGroups().get(i).timingsToString());
        }
    }

    private void initRecycleView(){
        /**
         * for initializing the the recycler view
         */
        adaptor = new GroupsRecyclerViewAdaptor(this,mIds,mTeacherNames,mTimings);
        recyclerView.setAdapter(adaptor);
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
        if (view == null) return;

        //OK button
        if (view.getId() == R.id.btn_fill_courses_ok){
            if (editTextCourseId.getText().toString().isEmpty()){
                Toast.makeText(this, "id field can't be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editTextCourseName.getText().toString().isEmpty()){
                Toast.makeText(this, "course name field can't be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            ArrayList<ModelCourse> courses = db.readCourses();
            String newName = editTextCourseName.getText().toString();
            int newId = Integer.valueOf(editTextCourseId.getText().toString());
            if (Action == "CREATE_COURSE"){
                /**
                 * need to add extra checks for the correct operation of db
                 */
                for (int i=0;i<courses.size();i++){
                    if (courses.get(i).getName() == newName){
                        Toast.makeText(this, "Error:Duplicate name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ( courses.get(i).getId() == newId) {
                        Toast.makeText(this, "Error:Duplicate id", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mCourse.setId(newId);
                mCourse.setName(newName);
                db.addCourse(mCourse);
            }
            else if (Action == "EDIT_COURSE"){
                /**
                 * need to add extra checks for the correct operation of db
                 */
                for (int i=0;i<courses.size();i++){
                    if (courses.get(i).getName() == newName && mCourse.getName() != newName) {
                        Toast.makeText(this, "Error:Duplicate name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ( courses.get(i).getId() == newId && mCourse.getId() != newId){
                        Toast.makeText(this, "Error:Duplicate id", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mCourse.setId(newId);
                mCourse.setName(newName);
                db.deleteCourse(courseId);
                db.addCourse(mCourse);
            }
            Intent intent = new Intent(this,ActivityViewCourses.class);
            setResult(RESULTED_IN_OK,intent);
            finish();
        }

        //cancel Button
        else if (view.getId() == R.id.btn_fill_courses_cancel){
            Intent intent = new Intent(this,ActivityViewCourses.class);
            setResult(RESULTED_IN_CANCEL,intent);
            finish();
        }

        //add button
        else if (view.getId() == R.id.btn_add_groups){
            Intent intent = new Intent(this,ActivityEditGroups.class);
            intent.setAction("CREATE_GROUP");
            startActivityForResult(intent,CREATE_GROUP);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CREATE_GROUP && resultCode == ActivityEditGroups.RESULTED_IN_CANCEL){
            /**
             * do absolutely nothing
             */
        }
        else if (requestCode == CREATE_GROUP && resultCode == ActivityEditGroups.RESULTED_IN_OK){
            /**
             * we now need to extract the extras passed to us by intent and fill out the  group
             */
            ModelGroup group = (ModelGroup) data.getSerializableExtra("model group");
            // check for duplicate id
            for (int i=0 ;i <mCourse.getGroups().size() ;i++){
                if(mCourse.getGroups().get(i).getGroupId() == group.getGroupId()){
                    Toast.makeText(this, "Error:duplicate group id !", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            mCourse.addGroup(group);
     //       mIds.add(String.valueOf(group.getGroupId()));
     //       mTeacherNames.add(String.valueOf(group.getTeacherName()));
     //       mTimings.add(group.timingsToString());
            adaptor.addItem( String.valueOf(group.getGroupId()),group.getTeacherName(),group.timingsToString());
        }
        else if (requestCode == EDIT_GROUP && resultCode == ActivityEditGroups.RESULTED_IN_CANCEL){
            /**
             * do absolutely nothing
             */
        }
        else if (requestCode == EDIT_GROUP && resultCode == ActivityEditGroups.RESULTED_IN_OK){
            /**
             * we now need to extract the extras passed to us by intent and edit corresponding group
             */
            int position=data.getIntExtra("position",-1);
            if (position==-1){return;}
            ModelGroup group = (ModelGroup) data.getSerializableExtra("model group");
            /**
             * for editing a group i simply delete the old group using the functions i had implemented earlier
             * and then i add the new group which its info was sent from ActivityEditGroups
             */
            for (int i=0 ;i <mCourse.getGroups().size() ;i++){
                if(mCourse.getGroups().get(i).getGroupId() == group.getGroupId() && i != position){
                    Toast.makeText(this, "Error:duplicate group id !", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            notifyGroupDeleted(position);
            adaptor.deleteItem(position);

            mCourse.addGroup(group);
     //       mIds.add(String.valueOf(group.getGroupId()));
     //       mTeacherNames.add(String.valueOf(group.getTeacherName()));
     //       mTimings.add(group.timingsToString());
            adaptor.addItem( String.valueOf(group.getGroupId()),group.getTeacherName(),group.timingsToString());
        }
    }
    public void notifyGroupDeleted(int position){
        mCourse.deleteGroup(position);
    //    mIds.remove(position);
    //    mTimings.remove(position);
    //    mTeacherNames.remove(position);
    }
    public ModelGroup getGroupInfoAt(int position){ return mCourse.getGroups().get(position); }
}

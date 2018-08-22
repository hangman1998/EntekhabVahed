package javaherian.yousef.entekhabvahed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityViewCourses extends AppCompatActivity {
    /**
     * request codes to send for the next activity
     */
    public static final int CREATE_COURSE=0;
    public static final int EDIT_COURSE =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        // temporary code for test
        Intent intent = new Intent(this,ActivityFillCourses.class);
        intent.setAction("CREATE_COURSE");
        startActivityForResult(intent,CREATE_COURSE);
    }
}

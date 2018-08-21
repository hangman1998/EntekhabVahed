package javaherian.yousef.entekhabvahed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityViewCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        // temporary code for test
        Intent intent = new Intent(this,ActivityFillCourses.class);
        intent.putExtra("Create a new course",true);
        startActivity(intent);
    }
}

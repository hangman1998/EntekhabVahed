//package motamedi.mohammad.entekhabvahed;
//
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.support.v7.app.AlertController;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import java.util.Collections;
//
//import javaherian.yousef.entekhabvahed.R;
//
//import static android.support.v7.app.AlertController.*;
//
//public class ActivityEditCourses extends AppCompatActivity {
//
//    RecycleListView mListView;
//    String courseName;
//    @SuppressLint("WrongViewCast")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_courses);
//
//        mListView = findViewById(R.id.list_view);
//
//    }
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String name) {
//        this.courseName = name;
//    }
//
//
//
//    ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
//            android.R.layout.simple_list_item_1, Collections.singletonList(courseName));
//
//    public ArrayAdapter<String> getmAdapter() {
//        return mAdapter;
//    }
//
//
//}
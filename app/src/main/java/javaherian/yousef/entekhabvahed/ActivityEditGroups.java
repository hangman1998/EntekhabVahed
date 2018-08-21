package javaherian.yousef.entekhabvahed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import hoosmand.Database;

public class ActivityEditGroups extends AppCompatActivity {
    EditText    idEditText,teacherEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_groups);
        Database db = new Database(this);
    }
}

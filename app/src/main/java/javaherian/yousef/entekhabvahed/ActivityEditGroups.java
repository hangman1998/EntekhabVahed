package javaherian.yousef.entekhabvahed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import hoosmand.Database;

public class ActivityEditGroups extends AppCompatActivity {
    /**
     * result codes to send for preceding activity
     */
    public static final int RESULTED_IN_OK=0;
    public static final int RESULTED_IN_CANCEL=1;
    /**
     * actions which are coming from previous activity(fill courses) are
     * 1.CREATE_GROUP
     * 2.EDIT_GROUP
     */
    EditText    idEditText,teacherEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_groups);

    }
}

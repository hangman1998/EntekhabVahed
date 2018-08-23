package heidari.mohammad.entekhabvahed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javaherian.yousef.entekhabvahed.R;

public class ActivityEditRule extends AppCompatActivity {
    Spinner spin1;
    TextView name;
    TextView teacher;
    TextView course;
    TextView day;
    EditText name1;
    EditText teacher1;
    EditText course1;
    Spinner day1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityeditrule);
        findView();
        //we should initial  view of
        String i= getIntent().getExtras().getString("activity_id");
        Toast.makeText(ActivityEditRule.this, i, Toast.LENGTH_SHORT).show();





    }
    private void findView(){
        day=(TextView)findViewById(R.id.dTV);
        day1=(Spinner)findViewById(R.id.dSP);
        name1=(EditText)findViewById(R.id.nET);
        course1=(EditText)findViewById(R.id.cET);
        teacher1=(EditText)findViewById(R.id.tET);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.planets_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day1.setAdapter(adapter);

    }

}

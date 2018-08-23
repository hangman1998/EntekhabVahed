package motamedi.mohammad.entekhabvahed;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;

import javaherian.yousef.entekhabvahed.ActivityViewCourses;
import javaherian.yousef.entekhabvahed.R;

public class ActivityEditCourses extends AppCompatActivity implements View.OnClickListener{

    /**
     * FloatingActionButton fab = findViewById(R.id.fab);
     public RecyclerView mListView;
     public String courseName;
     @SuppressLint("WrongViewCast")
      */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       setContentView(R.layout.activity_edit_courses);

  //      mListView = findViewById(R.id.list_view);

    }
    @Override
    public void onClick(View view) {


        if(view==null){
            return;
        }
//        else if(view==fab){
 //           Intent intent=new Intent(this,ActivityViewCourses.class);
//            startActivity(intent);

 //       }
    }



}
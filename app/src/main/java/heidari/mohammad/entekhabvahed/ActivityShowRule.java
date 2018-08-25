package heidari.mohammad.entekhabvahed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javaherian.yousef.entekhabvahed.ModelRule;
import javaherian.yousef.entekhabvahed.R;

public class ActivityShowRule extends AppCompatActivity {
    private ListView listR;
    private List<ModelRule> listRule = new ArrayList<ModelRule>();
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityshowrule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        findView();
        //loadfunction for load information of rule and make them as button view in this activity and we should save y\the infprmation in onDestroy function


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityShowRule.this,ActivityEditRule.class);
                intent.putExtra("activity_id","new_rule");
                startActivity(intent);;

                listRule.add(new ModelRule("سلام"+i,1,3,1,"ali","ffg",1));
                i=i+1;
                ruleAdapter ruleAdapter = new ruleAdapter(ActivityShowRule.this,listRule);
                listR.setAdapter(ruleAdapter);


            }
        });
    }
    void findView(){
        listR = (ListView) findViewById(R.id.listRule);

    }
//    @Override
//    public void onClick(View v) {
//        if(v==null){
//            return;
//        }
//        if(v.getId()==R.id.btn){
//            Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
//            intent.putExtra("activity_id",2);
//            startActivity(intent);
//
//        }
//        else{
//           // Toast.makeText(MainActivity.this, "salaam2", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //we should save the information in onDestroy function

    }


}

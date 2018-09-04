package heidari.mohammad.entekhabvahed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    public static final int CREATE_RULE=0;
    public static final int EDIT_RULE =1;
    private RecyclerView recyclerView;
    private EditRuleAdaptar adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rule);
        findView();
        initListView();
        initListView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityShowRule.this,ActivityEditRule.class);
                intent.setAction("CREATE_RULE");
                startActivityForResult(intent,CREATE_RULE);
            }
        });
    }
    private void findView(){
        recyclerView=findViewById(R.id.rules_recycler_view);

    }
    private void initListView(){
        adaptor = new EditRuleAdaptar(this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    };
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CREATE_RULE && resultCode == ActivityEditRule.RESULTED_IN_CANCEL) {
            /**
             * do absolutely nothing
             */
        } else if (requestCode == CREATE_RULE && resultCode == ActivityEditRule.RESULTED_IN_OK) {
            //initListView();
            adaptor.notifyItemAdded();
        } else if (requestCode == EDIT_RULE && resultCode == ActivityEditRule.RESULTED_IN_CANCEL) {
            /**
             * do absolutely nothing
             */
        } else if (requestCode == EDIT_RULE && resultCode == ActivityEditRule.RESULTED_IN_OK) {
           // initListView();
            adaptor.notifyItemEdited();
        }
    }
}

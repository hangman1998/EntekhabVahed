package heidari.mohammad.entekhabvahed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

import javaherian.yousef.entekhabvahed.ModelRule;
import javaherian.yousef.entekhabvahed.R;

public class ruleAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context1 ;
    private List<ModelRule> listRule;
    private int i;
    public ruleAdapter(Context context1, List<ModelRule> listRule) {
        this.context1 = context1;
        this.listRule = listRule;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ModelRule entery = listRule.get(i);

        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context1);
            view = inflater.inflate(R.layout.rule_item,null);
        }
        Button btn1 = (Button) view .findViewById(R.id.btn);
        btn1.setText(entery.getName());
        btn1.setOnClickListener(this);
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(this,"salaam",Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "salaam", Toast.LENGTH_SHORT).show();
////                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view ;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return listRule.get(i);
    }

    @Override
    public int getCount() {
        return listRule.size();
    }
    @Override
    public void onClick(View v) {
        if(v==null){
            return;
        }
        if(v.getId()==R.id.btn){
            Intent intent = new Intent(context1,ActivityEditRule.class);
            intent.putExtra("activity_id",((Button) v).getText());
            context1.startActivity(intent);

        }
        else{
            // Toast.makeText(MainActivity.this, "salaam2", Toast.LENGTH_SHORT).show();
        }
    }
}

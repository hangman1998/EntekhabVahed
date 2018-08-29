package heidari.mohammad.entekhabvahed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import javaherian.yousef.entekhabvahed.ModelRule;
import javaherian.yousef.entekhabvahed.R;

import static javaherian.yousef.entekhabvahed.Global.db;

public class ruleAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context1 ;
    private List<ModelRule> listRule;
    private int i;
    public ruleAdapter(Context context1) {
        this.context1 = context1;
        listRule=db.readRule();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ModelRule entery = listRule.get(i);

        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context1);
            view = inflater.inflate(R.layout.rule_item,null);
           // holder = new RecyclerView.ViewHolder();

            // ... DO WHAT YOU NEED HERE
           //holder.linearLayoutContainer = (LinearLayout) row.findViewById(R.id.);
            // Set the position as a Tag for the view
          //  holder.linearLayoutContainer.setTag(position);
        }
        Button btn1 = (Button) view .findViewById(R.id.btn);
        btn1.setText(entery.getName());
        btn1.setOnClickListener(this);
        btn1.setOnLongClickListener(new ruleAdapter.onLongClickListener(entery.getName()));
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
    public class onLongClickListener implements View.OnLongClickListener {
        private String name;

        public onLongClickListener(String name) {
            this.name = name;
        }

        @Override
        public boolean onLongClick(View v) {
            /**
             * here we initialize a dialog for deleting the corresponding course
             */
            if(v==null){
                return false;
            }
            if(v.getId()==R.id.btn){
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context1)
                        //set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                /**
                                 * here is the deleting code
                                 */
                                //deleteItem(position);
                                db.deleteRule(name);


                                dialog.dismiss();
                            }

                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                myQuittingDialogBox.show();
                return true;

            }
            else{
                // Toast.makeText(MainActivity.this, "salaam2", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
    }
//    @Override
//    public boolean onLongClick(View v){
//        if(v==null){
//            return false;
//        }
//        if(v.getId()==R.id.btn){
//            AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context1)
//                    //set message, title, and icon
//                    .setTitle("Delete")
//                    .setMessage("Do you want to Delete")
//                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int whichButton) {
//                            /**
//                             * here is the deleting code
//                             */
//                            //deleteItem(position);
//
//                            dialog.dismiss();
//                        }
//
//                    })
//                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .create();
//            myQuittingDialogBox.show();
//            return true;
//
//        }
//        else{
//            // Toast.makeText(MainActivity.this, "salaam2", Toast.LENGTH_SHORT).show();
//        }
//
//    }


}

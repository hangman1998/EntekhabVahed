package heidari.mohammad.entekhabvahed;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javaherian.yousef.entekhabvahed.ActivityFillCourses;
import javaherian.yousef.entekhabvahed.ActivityViewCourses;
import javaherian.yousef.entekhabvahed.ModelCourse;
import javaherian.yousef.entekhabvahed.ModelRule;
import javaherian.yousef.entekhabvahed.R;

import static javaherian.yousef.entekhabvahed.Global.db;

public class EditRuleAdaptar extends RecyclerView.Adapter<EditRuleAdaptar.ViewHolder> {
    //private ArrayList<String> names;
    private ArrayList<ModelRule> rules;
    private Context mContext;
    private int positionOfItemToBeEdited;
    private String ruleNameOfItemToBeEdited;
    public EditRuleAdaptar(Context mContext) {
        this.mContext = mContext;
        rules = db.readRule();
       // names = new ArrayList<>();
      //  for (int i = 0; i < courses.size(); i++) names.add(courses.get(i).getName());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rule_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EditRuleAdaptar.ViewHolder holder, int position) {
        holder.name.setText(rules.get(position).getName());
        /**
         * below code may change in future
         */
        holder.name.setOnClickListener(new EditRuleAdaptar.onClickListener(position));
        holder.name.setOnLongClickListener(new EditRuleAdaptar.onLongClickListener(position));
    }

    @Override
    public int getItemCount() {
       return rules.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button name;
        private LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.btn);
            layout = itemView.findViewById(R.id.rule_item_layout);
        }
    }


    public class onClickListener implements View.OnClickListener {
        private int position;

        public onClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            /**
             * here we send a request to activity fill courses for editing a course
             */
            positionOfItemToBeEdited=position;
            ruleNameOfItemToBeEdited=rules.get(position).getName();
            Intent intent = new Intent(mContext, ActivityEditRule.class);
            intent.putExtra("Activity_id", rules.get(position).getName());
            intent.setAction("EDIT_RULE");
            ((Activity) mContext).startActivityForResult(intent, ActivityShowRule.EDIT_RULE);
        }
    }


    public class onLongClickListener implements View.OnLongClickListener {
        private int position;

        public onLongClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View view) {
            /**
             * here we initialize a dialog for deleting the corresponding course
             */
            AlertDialog myQuittingDialogBox = new AlertDialog.Builder(mContext)
                    //set message, title, and icon
                    .setTitle("Delete")
                    .setMessage("Do you want to Delete")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            /**
                             * here is the deleting code
                             */
                            deleteItem(position);
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
    }

    public void notifyItemAdded() {
        rules=db.readRule();
       // names.add(courses.get(courses.size()-1).getName());
        notifyItemInserted(rules.size() - 1);
    }
    public void deleteItem(int position) {
        db.deleteRule(rules.get(position).getName());
        //names.remove(position);
        rules.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, rules.size());
    }
    public void notifyItemEdited() {
        //rules=db.readRule();
        //names.clear();
        //for (int i = 0; i < courses.size(); i++) names.add(courses.get(i).getName());
        notifyDataSetChanged();
    }
}

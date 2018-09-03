package javaherian.yousef.entekhabvahed;
/**
 * created by yousef
 * adaptor class for handling recycler view in activity fill courses
 *in constructor it receives a context and 3 arrays
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupsRecyclerViewAdaptor extends  RecyclerView.Adapter<GroupsRecyclerViewAdaptor.ViewHolder>{
    private ArrayList<String> mIds = new ArrayList<>();
    private ArrayList<String> mTeacherNames = new ArrayList<>();
    private ArrayList<String> mTimings = new ArrayList<>();
    private Context mContext;

    public GroupsRecyclerViewAdaptor(Context mContext,ArrayList<String> mIds, ArrayList<String> mTeacherNames, ArrayList<String> mTimings) {
        this.mIds = mIds;
        this.mTeacherNames = mTeacherNames;
        this.mTimings = mTimings;
        this.mContext=mContext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_view,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText("id: "+mIds.get(position) + " ,teacher: " +mTeacherNames.get(position)+" ,"+mTimings.get(position) );
        /**
         * below code may change in future
         */
        holder.layout.setOnClickListener(new onClickListener(position));
        holder.layout.setOnLongClickListener(new onLongClickListener(position));
    }

    @Override
    public int getItemCount() {
        return mIds.size();
    }

    public void addItem( String id, String teacherName , String timings){
        mIds.add(id);
        mTeacherNames.add(teacherName);
        mTimings.add(timings);
        notifyItemInserted(mIds.size() - 1);
    }
    public void deleteItem(int position){
        mIds.remove(position);
        mTeacherNames.remove(position);
        mTimings.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mIds.size());
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private LinearLayout layout;
        /**
         * defining an inner class for holding a group item
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.groups_view_text);
            layout=itemView.findViewById(R.id.groups_view_layout);
        }
    }
    public class onClickListener implements View.OnClickListener {
        private int position;

        public onClickListener(int position) { this.position = position; }
        @Override
        public void onClick(View view) {
            /**
             * here we initialize a dialog for editing of the corresponding group details
             */
            Intent intent=new Intent(mContext,ActivityEditGroups.class);
            intent.putExtra("model group",((ActivityFillCourses)mContext).getGroupInfoAt(position));
            intent.putExtra("position", position);
            intent.setAction("EDIT_GROUP");
            ((Activity) mContext).startActivityForResult(intent,ActivityFillCourses.EDIT_GROUP);
        }
    }
    public  class onLongClickListener implements View.OnLongClickListener{
        private int position;

        public onLongClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View view) {
            /**
             * here we initialize a dialog for deleting the corresponding group
             */
            AlertDialog myQuittingDialogBox =new AlertDialog.Builder(mContext)
                    //set message, title, and icon
                    .setTitle("Delete")
                    .setMessage("Do you want to Delete")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            /**
                             * here is the deleting code
                             */
                            deleteItem(position);
                            ((ActivityFillCourses)mContext).notifyGroupDeleted(position);
                            dialog.dismiss();
                        }

                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); }
                    })
                    .create();
            myQuittingDialogBox.show();
            return true;
        }
    }
}

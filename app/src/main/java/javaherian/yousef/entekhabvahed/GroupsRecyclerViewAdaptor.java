package javaherian.yousef.entekhabvahed;
/**
 * created by yousef
 * adaptor class for handling recycler view in activity fill courses
 *in constructor it receives a context and 3 arrays
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
        holder.idTextView.setText(mIds.get(position));
        holder.teacherTextView.setText(mTeacherNames.get(position));
        holder.timingsTextView.setText(mTimings.get(position));
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idTextView,teacherTextView,timingsTextView;
        private LinearLayout layout;
        /**
         * defining an inner class for holding a group item
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            idTextView=itemView.findViewById(R.id.groups_view_id_text_view);
            teacherTextView=itemView.findViewById(R.id.groups_view_teacher_name_text_view);
            timingsTextView=itemView.findViewById(R.id.groups_view_timings_text_view);
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
            intent.putExtra("Position",position);
            mContext.startActivity(intent);
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
            return false;
        }
    }
}

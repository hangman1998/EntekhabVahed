package javaherian.yousef.entekhabvahed;
/**
 * created by yousef
 * course adaptor for handling recycler view of activity view courses
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static javaherian.yousef.entekhabvahed.Global.db;

public class CoursesRecyclerViewAdaptor extends RecyclerView.Adapter<CoursesRecyclerViewAdaptor.ViewHolder> {
    private ArrayList<String> names;
    private ArrayList<ModelCourse> courses;
    private Context mContext;
    private int positionOfItemToBeEdited;
    private int courseIdOfItemToBeEdited;
    public CoursesRecyclerViewAdaptor(Context mContext) {
        this.mContext = mContext;
        courses = db.readCourses();
        names = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) names.add(courses.get(i).getName());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(names.get(position));
        /**
         * below code may change in future
         */
        holder.layout.setOnClickListener(new CoursesRecyclerViewAdaptor.onClickListener(position));
        holder.layout.setOnLongClickListener(new CoursesRecyclerViewAdaptor.onLongClickListener(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.course_view_text);
            layout = itemView.findViewById(R.id.course_view_layout);
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
            courseIdOfItemToBeEdited=courses.get(position).getId();
            Intent intent = new Intent(mContext, ActivityFillCourses.class);
            intent.putExtra("course id", courses.get(position).getId());
            intent.setAction("EDIT_COURSE");
            ((Activity) mContext).startActivityForResult(intent, ActivityViewCourses.EDIT_COURSE);
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
        courses=db.readCourses();
        names.add(courses.get(courses.size()-1).getName());
        notifyItemInserted(names.size() - 1);
    }
    public void deleteItem(int position) {
        db.deleteCourse(courses.get(position).getId());
        names.remove(position);
        courses.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, names.size());
    }
    public void notifyItemEdited() {
        courses=db.readCourses();
        /**
         * to be completed
         */

    }
}


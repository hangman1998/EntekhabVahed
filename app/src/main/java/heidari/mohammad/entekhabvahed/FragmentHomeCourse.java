package heidari.mohammad.entekhabvahed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javaherian.yousef.entekhabvahed.R;

public class FragmentHomeCourse extends Fragment  {
    TextView course_time;
    TextView course_name;
    TextView teacher_name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View mainView = inflater.inflate(R.layout.custom_show_coursehome, container, false);
        course_time = mainView.findViewById(R.id.course_time);
        teacher_name =mainView.findViewById(R.id.teacher_name);
        course_name= mainView.findViewById(R.id.course_name);
        return mainView;

    }
}

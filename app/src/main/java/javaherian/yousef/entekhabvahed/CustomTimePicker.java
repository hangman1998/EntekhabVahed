package javaherian.yousef.entekhabvahed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

public class CustomTimePicker extends Fragment {
    Spinner spinner;
    TimePicker startTimePicker,finishTimePicker;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.custom_time_picker, container, false);
        //finding the views
        spinner = rootView.findViewById(R.id.custom_time_picker_spinner);
        startTimePicker = rootView.findViewById(R.id.custom_time_picker_start_time);
        finishTimePicker = rootView.findViewById(R.id.custom_time_picker_finish_time);
        startTimePicker.setIs24HourView(true);
        finishTimePicker.setIs24HourView(true);
        return rootView;
    }
}

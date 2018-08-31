package heidari.mohammad.entekhabvahed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javaherian.yousef.entekhabvahed.R;

public class FragmentHomeTable extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View mainView = inflater.inflate(R.layout.custom_show_course, container, false);
        textView = mainView.findViewById(R.id.a);
        return mainView;

    }

}

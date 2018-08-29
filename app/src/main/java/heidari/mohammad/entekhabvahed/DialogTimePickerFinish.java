package heidari.mohammad.entekhabvahed;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TimePicker;

import javaherian.yousef.entekhabvahed.R;

public class DialogTimePickerFinish extends Dialog implements View.OnClickListener {
    public ActivityEditRule c;
    public Dialog d;
    public Button yes, no;
    TimePicker timePicker;

    public DialogTimePickerFinish(ActivityEditRule a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.time_picker);
        yes = (Button) findViewById(R.id.time_picker_ok);
        no = (Button) findViewById(R.id.time_picker_cansel);
        timePicker = (TimePicker)findViewById(R.id.custom_time_picker);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);


    }
    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.time_picker_ok:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    c.setEditextFinish(timePicker.getHour(),timePicker.getMinute());

                }
                else {

                    c.setEditextFinish(timePicker.getCurrentHour(),timePicker.getCurrentHour());
                }
                dismiss();
                break;
            case R.id.time_picker_cansel:
                dismiss();
                break;
            default:
                break;
        }

    }

    public void setTimePicker(int h) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.timePicker.setHour(h/60);
            this.timePicker.setMinute(h%60);

        }
        else {

            this.timePicker.setCurrentHour(h/60);
            this.timePicker.setCurrentHour(h%60);

        }
    }
    public int getTimePickerH(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return this.timePicker.getHour();

        }
        else {

            return this.timePicker.getCurrentHour();

        }

    }
    public int getTimePickerM(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return this.timePicker.getMinute();

        }
        else {

            return  this.timePicker.getCurrentMinute();

        }

    }
}


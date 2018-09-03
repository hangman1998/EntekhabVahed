package javaherian.yousef.entekhabvahed;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class DialogContactUs extends Dialog implements View.OnClickListener {
    Context context;
    Button ok;

    public DialogContactUs(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_contact_us);
        findView();
    }
    @Override
    public void onClick (View v){
        if(v==null){
            return;
        }
        else if(v.getId()==R.id.info_ok_button) {
            dismiss();
        }
    }
    private void findView(){
        ok=findViewById(R.id.info_ok_button);
        ok.setOnClickListener(this);
    }
}

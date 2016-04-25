package org.zreo.zhihureader;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by kakin on 2016/4/25.
 */
public class LoginDialog extends Dialog {

    Context context;
    public LoginDialog(Context context) {
        super(context);
        this.context =context;
    }
    public LoginDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_login);
    }
}

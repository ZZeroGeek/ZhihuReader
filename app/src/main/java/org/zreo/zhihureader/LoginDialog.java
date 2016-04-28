package org.zreo.zhihureader;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kakin on 2016/4/25.
 */
public class LoginDialog extends Dialog {

    Context context;
    public EditText mUsernameView;
    public EditText mPasswordView;
    private Button btnlogin;
    private Button btnloginother;
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
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnloginother = (Button) findViewById(R.id.btnloginbyother);

        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        mUsernameView.addTextChangedListener(new UsernameWatcher());

        loginMission();
    }

    class UsernameWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Toast.makeText(getContext(),"已输入账号",Toast.LENGTH_SHORT).show();
// ---------------------------用addview方法
//            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.login_fragment);
//
//            EditText password = new EditText(getContext());
//            password.setId(R.id.password);
//            password.setHint("密码");
//
//            TextInputLayout mtextInputLayout = (TextInputLayout) findViewById(R.id.userInput);
//            int theWidth = mtextInputLayout.getWidth();
//
//            Toast.makeText(getContext(),"长度是"+theWidth,Toast.LENGTH_SHORT).show();
//            TextInputLayout textInputLayout = new TextInputLayout(getContext());
//            textInputLayout.setId(R.id.textinput);
//
//            TextInputLayout.LayoutParams editParams = new LinearLayout.LayoutParams(theWidth,TextInputLayout.LayoutParams.WRAP_CONTENT);
//            RelativeLayout.LayoutParams relaParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
//            RelativeLayout.LayoutParams btnloginParams = (RelativeLayout.LayoutParams) btnlogin.getLayoutParams();
//            RelativeLayout.LayoutParams otherloginParams = (RelativeLayout.LayoutParams) btnloginother.getLayoutParams();
//
//
//            relaParams.addRule(RelativeLayout.BELOW,R.id.userInput);
//
//            relaParams.addRule(RelativeLayout.ALIGN_START,R.id.userInput);
//            relaParams.addRule(RelativeLayout.ALIGN_LEFT);
//
//
//            btnloginParams.addRule(RelativeLayout.BELOW,R.id.textinput);
//            otherloginParams.addRule(RelativeLayout.BELOW,R.id.textinput);
//
//            btnlogin.setLayoutParams(btnloginParams);
//            btnloginother.setLayoutParams(otherloginParams);
//
//            relativeLayout.addView(textInputLayout,relaParams);
//            textInputLayout.addView(password,editParams);


            if (s.toString().getBytes().length != s.length()){
                mUsernameView.setError("只能输入数字和字母");
                s.delete(s.length()-1,s.length());
            }
            TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.passwordInput);
            textInputLayout.setVisibility(View.VISIBLE);




        }
    }

    class PasswordWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            String password = mPasswordView.getText().toString();

            if (password.getBytes().length!= password.length()){
                mPasswordView.setError("只能输入数字和字母");
                s.delete(password.length()-1,password.length());
            }
            if (!TextUtils.isEmpty(password)){

                btnlogin.setTextColor(getContext().getResources().getColor(R.color.canLogin));

                btnlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"登录...",Toast.LENGTH_SHORT).show();
                    }
                });
            }else {

                btnlogin.setTextColor(getContext().getResources().getColor(R.color.cannotLogin));
                btnlogin.setOnClickListener(null);
            }
        }
    }

    public void loginMission(){
        mPasswordView.addTextChangedListener(new PasswordWatcher());
    }

    @Override
    public void dismiss() {
        mPasswordView.removeTextChangedListener(new PasswordWatcher());
        mUsernameView.removeTextChangedListener(new UsernameWatcher());
        super.dismiss();
    }
}

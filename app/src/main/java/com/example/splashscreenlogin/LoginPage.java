package com.example.splashscreenlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends AppCompatActivity {

    private TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);

        username=findViewById(R.id.user_name);
        password=findViewById(R.id.password);

        Button signupbutton=findViewById(R.id.new_user);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginPage.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean validateUsername()
    {
        String userinput = username.getEditText().getText().toString().trim();
        if (userinput.isEmpty()) {
            username.setError("This field cannot be empty");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }
    public  boolean validatepassword(){
        String userpassword = password.getEditText().getText().toString();
        String passwordPattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (userpassword.isEmpty()) {
            password.setError("This field cannot be empty.");
            return false;
        }
        else if (!userpassword.matches(passwordPattern))
        {
            password.setError("password must be >8 A a special nospace");
            return false;
        }
        else {
            password.setError(null);
            return true;
        }
    }

    public  void toValidate(View v){
        validateUsername();
        validatepassword();
    }
}

package com.example.splashscreenlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private TextInputLayout fullname,username,email,getpassword,retypepassword;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign);

        fullname=findViewById(R.id.full_name);
        username=findViewById(R.id.user_name);
        email=findViewById(R.id.email);
        getpassword=findViewById(R.id.password);
        retypepassword=findViewById(R.id.retype_password);
        submit=findViewById(R.id.submit);

    }
    //validating inputs


    public boolean validatefullname() {
        String name = fullname.getEditText().getText().toString();
        if (name.isEmpty()) {
            fullname.setError("This field cannot be empty.");
            return false;
        }

        else {
            fullname.setError(null);
            return true;
        }
    }
    public boolean validateusername(){
        String userinput = username.getEditText().getText().toString().trim();

        if (userinput.isEmpty()) {
            username.setError("This field cannot be empty");
            return false;
        }
        else if (userinput.length() > 10) {
            username.setError("Username must be <=10");
            return  false;
        }
        else {
            username.setError(null);
            return true;
        }
    }
    public boolean validateuseremail() {
        String useremail = email.getEditText().getText().toString();
        String emailpattern="[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (useremail.isEmpty()) {
            email.setError("This field cannot be empty.");
            return false;
        }
        else if (!useremail.matches(emailpattern)){
            email.setError("Invalid email");
            return false;
        }
        else {
            email.setError(null);
            return true;
        }
    }
    public boolean validatepassword() {
        String userpassword = getpassword.getEditText().getText().toString();
        String passwordpattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (userpassword.isEmpty()) {
            getpassword.setError("This field cannot be empty.");
            return false;
        }
        else if (!userpassword.matches(passwordpattern))
        {
            getpassword.setError("password must be >8 A a special nospace");
            return false;
        }
        else {
            getpassword.setError(null);
            return true;
        }
    }
    public boolean validateretypepassword(){
        String userpassword = getpassword.getEditText().getText().toString().trim();
        String retype = retypepassword.getEditText().getText().toString().trim();
        if (retype.isEmpty()) {
            retypepassword.setError("This field cannot be empty.");
            return false;
        }
        else if (!userpassword.equals(retype)){
            retypepassword.setError("Password does not match");
            return false;
        }
        else {
            retypepassword.setError(null);
            return true;
        }
    }

    public void toValidate(View v){
        validatefullname();
        validateusername();
        validateuseremail();
        validatepassword();
        validateretypepassword();

    }


    //on clicking already have an account button
    public void toLogin(View v)
    {
        Intent intent=new Intent(SignUp.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}

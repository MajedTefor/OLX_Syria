package com.olx.smartlife_solutions.olx_syria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView errorEmailOrPhone, errorPassword;
    EditText email, pass;
    Button login;
    ImageButton showPasswordIB;
    int s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        errorEmailOrPhone = findViewById(R.id.errorEmailOrPhone);
        errorPassword = findViewById(R.id.errorPasswordLogin);
        email = findViewById(R.id.emailOrPhone);
        pass = findViewById(R.id.passwordLogin);
        showPasswordIB = findViewById(R.id.showPasswordIB);
        showPasswordIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s == 0)
                {
                    pass.setTransformationMethod(null);
                    showPasswordIB.setImageResource(R.mipmap.ic_eye_orange);
                    s = 1;
                }
                else{
                    pass.setTransformationMethod(new PasswordTransformationMethod());
                    showPasswordIB.setImageResource(R.mipmap.ic_eye_grey);
                    s = 0;
                }

            }
        });
        login = findViewById(R.id.loginBTN);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }
    public void Login() {
        if (isInputCorrect()) {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("Name", email.getText().toString());
                jsonObject.put("Password", pass.getText().toString());
                jsonObject.put("RememberMe", "true");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isInputCorrect() {
        boolean b = true;
        if (email.getText().toString().length() == 0) {
            errorEmailOrPhone.setText("Field is required");
            b = false;
        } else {
            boolean isEmail = Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
            boolean isPhone = Patterns.PHONE.matcher(email.getText().toString()).matches();
            if(isEmail || isPhone) {
                errorEmailOrPhone.setText("");
            } else {
                char c = email.getText().toString().charAt(0);
                if(c >= '0' && c <= '9') {
                    errorEmailOrPhone.setText("Incorrect Phone Number, try your E-mail");
                } else {
                    errorEmailOrPhone.setText("Incorrect E-mail Address, try your Phone");
                }
            }
        }
        if (pass.getText().toString().length() < 6) {
            errorPassword.setText("Password must contain 6 character at Least");
            b = false;
        }
        if (pass.getText().toString().length() == 0) {
            errorPassword.setText("Field is required");
            b = false;
        }
        return b;
    }
    public void goToTerms(View view) {
        startActivity(new Intent(this, Rules.class));
    }

    public void goToForgetPassword(View view) {
        startActivity(new Intent(this, ForgetPasswoedActivity.class));
    }
}

package com.olx.smartlife_solutions.olx_syria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    TextView emailOrPhone, password;
    TextView errorEmailOrPhone, errorPassword;
    EditText email, pass;
    Button login;
    CheckBox showCheck;
    // xTextView
    TextView xEmailOrPhone, xPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");

        emailOrPhone = (TextView) findViewById(R.id.yourEmailOrPhone);
        password = (TextView) findViewById(R.id.yourPasswordLogin);
        errorEmailOrPhone = (TextView) findViewById(R.id.errorEmailOrPhone);
        errorPassword = (TextView) findViewById(R.id.errorPasswordLogin);
        email = (EditText) findViewById(R.id.emailOrPhone);
        pass = (EditText) findViewById(R.id.passwordLogin);
        //
        xEmailOrPhone = (TextView) findViewById(R.id.xEmailOrPhone);
        xPassword = (TextView) findViewById(R.id.xPasswordLogin);
        //
        showCheck = (CheckBox) findViewById(R.id.showPassword);
        showCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    pass.setTransformationMethod(null);
                } else {
                    pass.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
        login = (Button) findViewById(R.id.loginBTN);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        textViewListeners();
        xTextViewListeners();
    }
    public void xTextViewListeners() {
        xEmailOrPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setText("");
                xEmailOrPhone.setVisibility(View.GONE);
            }
        });
        xPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.setText("");
                xPassword.setVisibility(View.GONE);
            }
        });
    }
    public void textViewListeners() {
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Object o = email.getText().toString();
                if ("".equals(o)) {
                    emailOrPhone.setText("");
                    xEmailOrPhone.setVisibility(View.GONE);
                } else {
                    emailOrPhone.setText("Your Name");
                    xEmailOrPhone.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Object o = email.getText().toString();
                if ("".equals(o)) {
                    errorEmailOrPhone.setText("Field is required");
                    xEmailOrPhone.setVisibility(View.GONE);
                } else {
                    errorEmailOrPhone.setText("");
                    xEmailOrPhone.setVisibility(View.VISIBLE);
                }
            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Object o = pass.getText().toString();
                if ("".equals(o)) {
                    password.setText("");
                    xPassword.setVisibility(View.GONE);
                } else {
                    password.setText("Your Password");
                    xPassword.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = pass.getText().toString();
                if ("".equals(o)) {
                    errorPassword.setText("Field is required");
                    xPassword.setVisibility(View.GONE);
                } else {
                    xPassword.setVisibility(View.VISIBLE);
                    if (pass.getText().toString().length() < 6) {
                        errorPassword.setText("Password must contain 6 character at Least");
                    } else {
                        errorPassword.setText("");
                    }
                }
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
            //JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, )
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
    @Override // back button
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return false;
    }
    public void backToRegister(View view) { // to Register
        startActivity(new Intent(this,RegisterActivity.class));
    }
    public void toggleShow(View view) { // toggle show password
        if(!(showCheck.isChecked())) {
            showCheck.setChecked(true);
        } else {
            showCheck.setChecked(false);
        }
    }
    public void goToTerms(View view) {
        startActivity(new Intent(this, Rules.class));
    }
}

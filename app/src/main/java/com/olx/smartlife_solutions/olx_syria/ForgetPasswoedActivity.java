package com.olx.smartlife_solutions.olx_syria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ForgetPasswoedActivity extends AppCompatActivity implements API_URLs {
    Button btnResetPassword;
    EditText emailResetPassword;
    TextView errorResetPassword;
    LinearLayout resetPasswordLayout, resetPasswordCompleted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passwoed);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        emailResetPassword = findViewById(R.id.emailResetPass);
        errorResetPassword = findViewById(R.id.errorResetPassword);
        resetPasswordLayout = findViewById(R.id.resetPassLayout);
        resetPasswordCompleted = findViewById(R.id.resetPasswordCompleted);
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInputCorrect()) {
                    /** TODO add forget password API request
                     * if request DONE show complete layout
                     */
                    resetPasswordLayout.setVisibility(View.GONE);
                    resetPasswordCompleted.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public boolean isInputCorrect() {
        String email = emailResetPassword.getText().toString();
        if (email.length() == 0) {
            errorResetPassword.setText("Field is required");
            return false;
        }
        boolean isEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (!isEmail) {
            errorResetPassword.setText("Incorrect Email Address");
            return false;
        }
        return true;
    }
}

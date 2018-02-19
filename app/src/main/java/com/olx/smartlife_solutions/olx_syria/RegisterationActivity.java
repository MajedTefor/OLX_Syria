package com.olx.smartlife_solutions.olx_syria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegisterationActivity extends AppCompatActivity implements API_URLs, AdapterView.OnItemSelectedListener {
    EditText name, email, phone, pass, rePass;
    Spinner spinner;
    String countryChooser = "";
    CheckBox agreeTerm;
    Button register;
    int positionCity;
    TextView errorName, errorEmail, errorPhone,
            errorPassword, errorRePassword, errorCountry, errorCheck;
    final List<String> countryName = new ArrayList<String>();
    final List<String> countryID = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        //
        name = findViewById(R.id.nameView);
        email = findViewById(R.id.emailView);
        phone = findViewById(R.id.phoneView);
        pass = findViewById(R.id.passwordView);
        rePass = findViewById(R.id.passwordView2);
        agreeTerm = findViewById(R.id.agreeCheckBox);
        register = findViewById(R.id.registerBTN);
        //
        errorName = findViewById(R.id.errorName);
        errorEmail = findViewById(R.id.errorEmail);
        errorPhone = findViewById(R.id.errorPhone);
        errorPassword = findViewById(R.id.errorPassword);
        errorRePassword = findViewById(R.id.errorRePassword);
        errorCountry = findViewById(R.id.errorCountry);
        errorCheck = findViewById(R.id.errorCheck);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        textViewListeners();
        setChooseCitySpinner();
    }

    public void textViewListeners() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = name.getText().toString();
                if ("".equals(o)) {
                    errorName.setText("Field is required");
                    return;
                }
                char c = name.getText().toString().charAt(0);
                if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                    errorName.setText("Incorrect Name, start with character");
                } else {
                    errorName.setText("");
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = email.getText().toString();
                if ("".equals(o)) {
                    errorEmail.setText("Field is required");
                } else {
                    errorEmail.setText("");
                }
            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = phone.getText().toString();
                if ("".equals(o)) {
                    errorPhone.setText("Field is required");
                } else {
                    errorPhone.setText("");
                }
            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = pass.getText().toString();
                if ("".equals(o)) {
                    errorPassword.setText("Field is required");
                } else {
                    if (pass.getText().toString().length() < 6) {
                        errorPassword.setText("Password must contain 6 characters, at Least with ONE digit");
                    } else {
                        errorPassword.setText("");
                    }
                }
            }
        });
        rePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = rePass.getText().toString();
                if ("".equals(o)) {
                    errorRePassword.setText("Field is required");
                } else {
                    errorRePassword.setText("");
                }
            }
        });
    }
    public void Register() {
        boolean b = isInputCorrect();
        if (b && agreeTerm.isChecked()) {
            // API
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("Name", name.getText().toString());
                jsonObject.put("RememberMe", "true");
                jsonObject.put("AreaID", countryID.get(positionCity));
                jsonObject.put("Phone", phone.getText().toString());
                jsonObject.put("Email", email.getText().toString());
                jsonObject.put("Password", pass.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Register_URL, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            Toast.makeText(RegisterationActivity.this, "Check your Email, Please :)", Toast.LENGTH_LONG).show();
                            RegisterationActivity.this.finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(RegisterationActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                    volleyError.getStackTrace();
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }
    public boolean isInputCorrect() {
        boolean b = true;
        if (name.getText().toString().length() == 0) {
            errorName.setText("Field is required");
            b = false;
        } else {
            char c = name.getText().toString().charAt(0);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                errorName.setText("Incorrect Name, start with character");
                b = false;
            }
        }
        if (email.getText().toString().length() == 0) {
            errorEmail.setText("Field is required");
            b = false;
        } else {
            boolean isEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
            if (!(isEmail)) {
                errorEmail.setText("Incorrect E-mail Address");
                b = false;
            }
        }
        if (phone.getText().toString().length() == 0) {
            errorPhone.setText("Field is required");
            b = false;
        } else {
            boolean isPhone = Patterns.PHONE.matcher(phone.getText().toString()).matches();
            if (!(isPhone)) {
                errorPhone.setText("Incorrect E-mail Address");
                b = false;
            }
        }
        boolean conntainDigit = false;
        char[] passChars = pass.getText().toString().toCharArray();
        for (char aC : passChars) {
            if (aC >= '0' && aC <= '9') {
                conntainDigit = true;
                break;
            }
        }
        if (pass.getText().toString().length() < 6 || !conntainDigit) {
            errorPassword.setText("Password must contain 6 character, at Least with ONE digit");
            b = false;
        }
        if (pass.getText().toString().length() == 0) {
            errorPassword.setText("Field is required");
            b = false;
        }
        if (rePass.getText().toString().length() == 0) {
            errorRePassword.setText("Field is required");
            b = false;
        }
        if (countryChooser.length() == 0) {
            errorCountry.setText("Field is required");
            b = false;
        }
        if (pass.getText().toString().length() >= 6) {
            if (!(pass.getText().toString().equals(rePass.getText().toString()))) {
                errorRePassword.setText("No much Passwords");
                b = false;
            }
        }
        if (!(agreeTerm.isChecked())) {
            errorCheck.setText("Field is required");
            b = false;
        } else {
            errorCheck.setText("");
        }
        Object o = phone.getText().toString();
        if(o.toString().length() != 0) {
            boolean isPhone = Patterns.PHONE.matcher((CharSequence) o).matches();
            if (isPhone) {
                errorPhone.setText("");
            } else {
                errorPhone.setText("Incorrect Phone Number");
            }
        }
        return b;
    }
    public void toggleCheck(View view) {
        if (!(agreeTerm.isChecked())) {
            agreeTerm.setChecked(true);
        } else {
            agreeTerm.setChecked(false);
        }
    }
    public void goToRules(View view) {
        startActivity(new Intent(this, Rules.class));
    }
    @Override // back button
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return false;
    }
    public void setChooseCitySpinner() {
        spinner = findViewById(R.id.selectCountry);
        spinner.setOnItemSelectedListener(this);
        countryName.add("Where are you from ??");
        countryID.add("No City Choose");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(CITY_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject cityJSON = jsonArray.getJSONObject(i);
                        countryName.add(cityJSON.getString("name"));
                        countryID.add(cityJSON.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        positionCity = position;
        if (position == 0) {
            countryChooser = "";
            return;
        }
        countryChooser = parent.getItemAtPosition(position).toString();
        errorCountry.setText("");
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        countryChooser = "";
    }
}

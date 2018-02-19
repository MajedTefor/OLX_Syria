package com.olx.smartlife_solutions.olx_syria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.LinearLayout;
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

import static com.olx.smartlife_solutions.olx_syria.MainApp.checkInternet;
import static com.olx.smartlife_solutions.olx_syria.MainApp.fragmentTransaction;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, API_URLs {
    Spinner spinner;
    String countryChooser = "";
    EditText name, email, phone, pass, rePass;
    CheckBox agreeTerm;
    Button register;

    // TextViews to show titles and error letters
    TextView yourName, yourEmail, yourPhone, yourPassword, yourRePassword, yourCountry;
    TextView errorName, errorEmail, errorPhone, errorPassword, errorRePassword, errorCountry, errorCheck;

    // X TextViews to clear EditText and it will be GONE
    TextView xName, xEmail, xPhone, xPassword, xRePassword;

    // City
    int positionCity;
    //
    LinearLayout registerForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // To display back icon and setting Activity Title
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Register");
        // EditTexts and TextView
        name = findViewById(R.id.nameView);
        email = findViewById(R.id.emailView);
        phone = findViewById(R.id.phoneView);
        pass = findViewById(R.id.passwordView);
        rePass = findViewById(R.id.passwordView2);
        agreeTerm = findViewById(R.id.agreeCheckBox);
        register = findViewById(R.id.registerBTN);
        yourName = findViewById(R.id.yourName);
        yourEmail = findViewById(R.id.yourEmail);
        yourPhone = findViewById(R.id.yourPhone);
        yourPassword = findViewById(R.id.yourPassword);
        yourRePassword = findViewById(R.id.yourRePassword);
        yourCountry = findViewById(R.id.yourCountry);
        errorName = findViewById(R.id.errorName);
        errorEmail = findViewById(R.id.errorEmail);
        errorPhone = findViewById(R.id.errorPhone);
        errorPassword = findViewById(R.id.errorPassword);
        errorRePassword = findViewById(R.id.errorRePassword);
        errorCountry = findViewById(R.id.errorCountry);
        errorCheck = findViewById(R.id.errorCheck);
        // x TextView
        xName = findViewById(R.id.xName);
        xEmail = findViewById(R.id.xEmail);
        xPhone = findViewById(R.id.xPhone);
        xPassword = findViewById(R.id.xPassword);
        xRePassword = findViewById(R.id.xRePassword);
        // Click on Register Button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        // EditTexts Listeners
        textViewListeners();
        xViewListeners();
        // check internet
        if (checkInternetConnection()) {
            setChooseCitySpinner();
        }
    }
    final List<String> countryName = new ArrayList<String>();
    final List<String> countryID = new ArrayList<String>();
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
                fragmentTransaction = getFragmentManager().beginTransaction();
              //  fragmentTransaction.replace(R.id.registerActivityView, noInternetFragment);
                fragmentTransaction.commit();
                registerForm = findViewById(R.id.registerForm);
                registerForm.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonArrayRequest);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    public boolean checkInternetConnection() {
        if (!checkInternet.isConnected()) {
            fragmentTransaction = getFragmentManager().beginTransaction();
         //   fragmentTransaction.replace(R.id.registerActivityView, noInternetFragment);
            fragmentTransaction.commit();
            registerForm = findViewById(R.id.registerForm);
            registerForm.setVisibility(View.GONE);
            return false;
        }
        return true;
    }
    public void xViewListeners() {
        xName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                xName.setVisibility(View.GONE);
            }
        });
        xEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setText("");
                xEmail.setVisibility(View.GONE);
            }
        });
        xPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setText("");
                xPhone.setVisibility(View.GONE);
            }
        });
        xPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.setText("");
                xPassword.setVisibility(View.GONE);
            }
        });
        xRePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rePass.setText("");
                xRePassword.setVisibility(View.GONE);
            }
        });
    }
    public void textViewListeners() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Object o = name.getText().toString();
                if ("".equals(o)) {
                    yourName.setText("");
                    xName.setVisibility(View.GONE);
                } else {
                    yourName.setText("Your Name");
                    xName.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = name.getText().toString();
                if ("".equals(o)) {
                    errorName.setText("Field is required");
                    xName.setVisibility(View.GONE);
                    return;
                }
                xName.setVisibility(View.VISIBLE);
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
                Object o = email.getText().toString();
                if ("".equals(o)) {
                    yourEmail.setText("");
                    xEmail.setVisibility(View.GONE);
                } else {
                    yourEmail.setText("Your E-mail");
                    xEmail.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = email.getText().toString();
                if ("".equals(o)) {
                    errorEmail.setText("Field is required");
                    xEmail.setVisibility(View.GONE);
                } else {
                    errorEmail.setText("");
                    xEmail.setVisibility(View.VISIBLE);
                }
            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Object o = phone.getText().toString();
                if ("".equals(o)) {
                    yourPhone.setText("");
                    xPhone.setVisibility(View.GONE);
                } else {
                    yourPhone.setText("Your Phone");
                    xPhone.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = phone.getText().toString();
                if ("".equals(o)) {
                    errorPhone.setText("Field is required");
                    xPhone.setVisibility(View.GONE);
                } else {
                    errorPhone.setText("");
                    xPhone.setVisibility(View.VISIBLE);
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
                    yourPassword.setText("");
                    xPassword.setVisibility(View.GONE);
                } else {
                    yourPassword.setText("Your Password");
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
                Object o = rePass.getText().toString();
                if ("".equals(o)) {
                    yourRePassword.setText("");
                    xRePassword.setVisibility(View.GONE);
                } else {
                    yourRePassword.setText("ReType Password");
                    xRePassword.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                Object o = rePass.getText().toString();
                if ("".equals(o)) {
                    errorRePassword.setText("Field is required");
                    xRePassword.setVisibility(View.GONE);
                } else {
                    errorRePassword.setText("");
                    xRePassword.setVisibility(View.VISIBLE);
                }
            }
        });
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
                            Toast.makeText(RegisterActivity.this, "Check your Email, Please :)", Toast.LENGTH_LONG).show();
                            RegisterActivity.this.finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(RegisterActivity.this, "Try again", Toast.LENGTH_SHORT).show();
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
    @Override // back button
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return false;
    }
    @Override // Spinner item selected
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        positionCity = position;
        if (position == 0) {
            countryChooser = "";
            yourCountry.setText("");
            return;
        }
        countryChooser = parent.getItemAtPosition(position).toString();
        yourCountry.setText("Your Country");
        errorCountry.setText("");
    }
    @Override // no item selected in spinner
    public void onNothingSelected(AdapterView<?> parent) {
        countryChooser = "";
    }
}

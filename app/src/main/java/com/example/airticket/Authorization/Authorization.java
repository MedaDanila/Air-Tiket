package com.example.airticket.Authorization;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.airticket.R;
import com.example.airticket.list.TicketList;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class Authorization extends AppCompatActivity {
    TextInputLayout LoginET, PasswordET;
    String Token, Login, Password,Error;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        mRequestQueue = Volley.newRequestQueue(this);
        LoginET = findViewById(R.id.Login);
        PasswordET = findViewById(R.id.Password);
    }

    private void parseJSON() {
        Login = Objects.requireNonNull(LoginET.getEditText()).getText().toString();
        Password = Objects.requireNonNull(PasswordET.getEditText()).getText().toString();
        String url = "http://drsoak.pythonanywhere.com/auth?login=" + Login + "&password=" + Password + "";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Token = response.getString("token");
                    Intent intent = new Intent(Authorization.this, TicketList.class);
                    intent.putExtra("Token", Token);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    try {
                        Toast.makeText(Authorization.this, Error = response.getString("result"), Toast.LENGTH_LONG).show();
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    public void Enter(View view) {
        if (!validateLogin() | !validatePass()) {
            return;
        } else {
            parseJSON();
        }
    }

    private Boolean validateLogin() {
        String valLogin = LoginET.getEditText().getText().toString();
        if (valLogin.isEmpty()) {
            LoginET.setError("Поле не может быть пустым");
            return false;

        } else {
            LoginET.setError(null);
            LoginET.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePass() {
        String valPass = PasswordET.getEditText().getText().toString();
        if (valPass.isEmpty()) {
            PasswordET.setError("Поле не может быть пустым");
            return false;

        } else {
            PasswordET.setError(null);
            PasswordET.setErrorEnabled(false);
            return true;
        }
    }
}
package com.example.airticket.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.airticket.Authorization.Authorization;
import com.example.airticket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TicketList extends AppCompatActivity {
    String Token,Error;
    private RecyclerView mRecyclerView;
    private TicketListAdapter mExampleAdapter;
    private ArrayList<TicketListItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketlist);
        mRecyclerView = findViewById(R.id.recyclerview);
        Token = getIntent().getStringExtra("Token");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "http://drsoak.pythonanywhere.com/get?token=" + Token + "";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        String from = data.getString("from");
                        String dest = data.getString("dest");
                        String date = data.getString("date");
                        int cost = data.getInt("cost");
                        mExampleList.add(new TicketListItem(from, dest, date, cost));
                        sortByCost();
                    }
                    mExampleAdapter = new TicketListAdapter(TicketList.this, mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                } catch (JSONException e) {
                    try {
                        Toast.makeText(TicketList.this, Error = response.getString("result"), Toast.LENGTH_LONG).show();
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

    private void sortByCost() {
        Collections.sort(mExampleList, new Comparator<TicketListItem>() {
            @Override
            public int compare(TicketListItem o1, TicketListItem o2) {
                if (o1.mCost() > o2.mCost()) {
                    return 1;
                } else if (o1.mCost() < o2.mCost()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }
}
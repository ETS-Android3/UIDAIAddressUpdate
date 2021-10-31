package com.example.uidaiaddressupdate.requestaddress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.uidaiaddressupdate.R;

public class RequestHome extends AppCompatActivity {

    private RecyclerView oldRequestsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_home);

    }
}
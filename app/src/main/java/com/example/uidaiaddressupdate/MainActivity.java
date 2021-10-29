package com.example.uidaiaddressupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uidaiaddressupdate.landlord.LandlordActivity;
import com.example.uidaiaddressupdate.requestaddress.RequestHome;
import com.example.uidaiaddressupdate.sharecode.ShowShareCode;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton requestAddress, viewRequests, shareCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestAddress = (AppCompatButton) findViewById(R.id.main_request_address_button);
        viewRequests = (AppCompatButton) findViewById(R.id.view_requests_button);
        shareCode = (AppCompatButton) findViewById(R.id.view_share_code_btn);
        requestAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RequestHome.class));
            }
        });


        viewRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LandlordActivity.class));
            }
        });

        shareCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowShareCode.class));
            }
        });
    }
}
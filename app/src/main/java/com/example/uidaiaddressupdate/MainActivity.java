package com.example.uidaiaddressupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uidaiaddressupdate.requestaddress.RequestHome;

public class MainActivity extends AppCompatActivity {
    private Button requestAddress, viewRequests, shareCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestAddress = (Button) findViewById(R.id.main_request_address_button);
        requestAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RequestHome.class));
            }
        });
    }
}
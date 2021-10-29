package com.example.uidaiaddressupdate.sharecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.sharecode.ui.main.ShowShareCodeFragment;

public class ShowShareCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_share_code_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ShowShareCodeFragment.newInstance())
                    .commitNow();
        }
    }
}
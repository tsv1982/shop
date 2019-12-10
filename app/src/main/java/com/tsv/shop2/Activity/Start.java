package com.tsv.shop2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.tsv.shop2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Start extends AppCompatActivity {

    @BindView(R.id.startET)
    EditText editText;

    @OnClick(R.id.enterBtn)
    public void btn() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor;
        if (editText.getText().toString().equals("admin")) {
            editor = sharedPreferences.edit();
            editor.putString("IdOrUser", editText.getText().toString());
            editor.apply();
        } else {
            editor = sharedPreferences.edit();
            editor.putString("IdOrUser", editText.getText().toString());
            editor.apply();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();

        ButterKnife.bind(this);
    }
}

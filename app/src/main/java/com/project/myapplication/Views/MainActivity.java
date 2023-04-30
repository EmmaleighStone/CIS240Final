package com.project.myapplication.Views;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.project.myapplication.Models.Account;
import com.project.myapplication.Controller.AppController;
import com.project.myapplication.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imgFront);
        imageView.getLayoutParams().width = 75;
        imageView.getLayoutParams().height = 75;
        EditText editUsername = (EditText) findViewById(R.id.editUsername);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        TextView txtError = (TextView) findViewById(R.id.txtError);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        AppController.makeDummyData(AppController.accounts);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                boolean success = AppController.checkLogin(AppController.accounts, username, password, txtError);
                if (success == true)
                {
                    int accountId = AppController.checkAccountId(AppController.accounts, username, password);
                    SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("accountId", accountId);
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
                else
                {
                    txtError.setText("We do not have an account with that username and/or password. Please try again.");
                }
            }});

    }
}
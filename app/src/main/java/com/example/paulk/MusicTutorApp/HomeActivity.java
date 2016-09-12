package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HomeActivity extends Activity {
    Button btnSignIn, btnSignUp;
    DatabaseHandler db;
    //LoginDatabaseAdaptor loginDataBaseAdapter;
    //test comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DatabaseHandler(this);
        db.getWritableDatabase();

        Log.i("TAG", "OnCreate fired in Home activity!!");
/*
        loginDataBaseAdapter = new LoginDatabaseAdaptor(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();*/

        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intentSignUP = new Intent(getApplicationContext(),
                        SignUpActivity.class);
                startActivity(intentSignUP);
            }
        });
    }

    public void signIn(View V) {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.activity_login);
        dialog.setTitle("Login");
        final EditText editTextUserName = (EditText) dialog
                .findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText) dialog
                .findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String storedPassword = db.getPassword(userName);
                Log.i("TAG", "Getting password for user : " + userName + ". Password is : " + storedPassword);
                if (password.equals(storedPassword)) {
                    Toast.makeText(HomeActivity.this,
                            "Congrats: Login Successful", Toast.LENGTH_LONG)
                            .show();
                    dialog.dismiss();

                    SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
                    SharedPreferences.Editor edit = userDetails.edit();
                    edit.clear();
                    edit.putString("username", userName);
                    edit.commit();

                    Intent main = new Intent(HomeActivity.this, MainMenuActivity.class);
                    main.putExtra("currentUser", userName);
                    startActivity(main);
                } else {
                    Toast.makeText(HomeActivity.this,
                            "User Name or Password does not match",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}

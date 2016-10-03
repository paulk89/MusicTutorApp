package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class HomeActivity extends Activity {
    Button btnSignIn, btnSignUp;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DatabaseHandler(this);
        db.getWritableDatabase();

        Log.i("TAG", "OnCreate fired in Home activity!!");

        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);
    }

    //prompt the user to sign in
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

                int userID = db.getUserID(userName);


                if (userID == -1) {
                    Toast.makeText(HomeActivity.this,
                            "User does not exist! Check the spelling! (Case senstive)", Toast.LENGTH_LONG)
                            .show();
                } else {

                    Log.i("TAG", "Getting password for user : " + userName + ". Password is : " + storedPassword);
                    if (password.equals(storedPassword)) {
                        Toast.makeText(HomeActivity.this,
                                "Login Successful!", Toast.LENGTH_LONG)
                                .show();
                        dialog.dismiss();


                        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
                        SharedPreferences.Editor edit = userDetails.edit();
                        edit.clear();
                        edit.putString("username", userName);
                        edit.putInt("userID", userID);
                        edit.commit();

                        Intent main = new Intent(HomeActivity.this, MainMenuActivity.class);
                        main.putExtra("currentUser", userName);
                        startActivity(main);
                    } else {
                        Toast.makeText(HomeActivity.this,
                                "Username and Password do not match",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        dialog.show();
    }

    //prompt the user to create an account
    public void signUP(View v){

        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.activity_signup);
        dialog.setTitle("Create Account");

        final EditText editTextUserName = (EditText) dialog
                .findViewById(R.id.editTextUserName);
        final EditText editTextPassword = (EditText) dialog
                .findViewById(R.id.editTextPassword);
        final EditText editTextConfirmPassword = (EditText) dialog
                .findViewById(R.id.editTextConfirmPassword);

        Button btnCreateAccount = (Button) dialog.findViewById(R.id.buttonCreateAccount);

        //check credentails being entered and make sure no fields are left blank
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText()
                        .toString();
                if (userName.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field has been left vacant",
                            Toast.LENGTH_LONG).show();
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Passwords do not match!", Toast.LENGTH_LONG)
                            .show();
                } else if ((!(userName.equals(""))) && (password.equals(confirmPassword))) {

                    db.addUser(userName, password, 1);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created!", Toast.LENGTH_LONG)
                            .show();
                    dialog.dismiss();

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

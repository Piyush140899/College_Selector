package com.example.basicfragmentbottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {
    SharedPreferences sp;

    public static final String EXTRA_MESSAGE = "com.example.cs.MESSAGE";

    boolean backbutton=false;
    public Button btn;
    static String user_id;
    EditText emailid,password;
    public FirebaseAuth mAuth;
    static String m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getBoolean("logged",false)){
            finish();
            Intent intent = new Intent(Login.this, MainActivity.class);
            user_id = sp.getString("user_id","");
            startActivity(intent);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verify();

            }
        });


        TextView tv=(TextView)findViewById(R.id.signup);

        tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });


        emailid = (EditText) findViewById(R.id.temail);
        password = (EditText) findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();




    }
    @Override
    public void onBackPressed() {
        if(backbutton) {
           // finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
           // super.onBackPressed();
            //return;
        }
        this.backbutton=true;
        Toasty.info(this, "Press back again to exit", Toasty.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backbutton=false;
            }
        },2000);
    }

    public void verify()
    {

        m = emailid.getText().toString();
        String n = password.getText().toString();

        if(m.isEmpty())
            Toasty.info(this, "Enter the email", Toasty.LENGTH_SHORT).show();
        else if(n.isEmpty())
            Toasty.info(this, "Enter the password", Toasty.LENGTH_SHORT).show();
        else {
            mAuth.signInWithEmailAndPassword(m, n)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toasty.success(getApplicationContext(),"Logged in successfully", Toasty.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                user_id = mAuth.getCurrentUser().getUid();
                                sp.edit().putBoolean("logged",true).apply();
                                sp.edit().putString("user_id",user_id).apply();
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toasty.error(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }
}

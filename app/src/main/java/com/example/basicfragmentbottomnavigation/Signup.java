package com.example.basicfragmentbottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Signup extends AppCompatActivity {

    public Button btn;
    private DocumentReference mDocRef;
    EditText emailid,password,password2,username;
    public FirebaseAuth mAuth;
    static String m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn=findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verify();

            }
        });
        emailid = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.pass1);
        password2 = (EditText) findViewById(R.id.pass2);
        username = (EditText) findViewById(R.id.username1);
        mAuth = FirebaseAuth.getInstance();
    }


    public void verify()
    {

        m = emailid.getText().toString();
        String n = password.getText().toString();
        String nn = password2.getText().toString();
        final String u_name = username.getText().toString();
        if(m.isEmpty())
            Toasty.info(this, "Enter the email", Toasty.LENGTH_SHORT).show();
        else if(n.isEmpty())
            Toasty.info(this, "Enter the password", Toasty.LENGTH_SHORT).show();
        else if(!n.equals(nn))
            Toasty.info(this, "Passwords don't match", Toasty.LENGTH_SHORT).show();
        else {
            mAuth.createUserWithEmailAndPassword(m, n)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toasty.success(getApplicationContext(),"Account created!",Toasty.LENGTH_SHORT).show();
                                String user_id = mAuth.getCurrentUser().getUid();
                                mDocRef = FirebaseFirestore.getInstance().collection("users").document(user_id);

                                Map<String,Object> dtos = new HashMap<String,Object>();
                                dtos.put("email",m);
                                dtos.put("username",u_name);
                                mDocRef.set(dtos);

                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toasty.error(getApplicationContext(), "Creation failed!", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }
}

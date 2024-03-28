package com.example.basicfragmentbottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.example.basicfragmentbottomnavigation.Login.user_id;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("login",MODE_PRIVATE);

        String message;
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        NavigationView navview = findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navview.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome(),"frag").commit();
            navview.setCheckedItem(R.id.navHome);
        }
        message = user_id;
        DocumentReference mDocRef = FirebaseFirestore.getInstance().collection("users").document(message);


        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {        Toasty.success(getApplicationContext(),"fetched!!",Toasty.LENGTH_SHORT).show();

                if(documentSnapshot.exists()){
                    String username = documentSnapshot.getString("username");
                    TextView textView = findViewById(R.id.myname);
                    textView.setText("Welcome, "+username.toUpperCase()+"\nhope you are having a good day!");



                }
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String tag = "frag";
        switch (menuItem.getItemId()){
            case R.id.navHome:
                getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_container,new FragmentHome(),tag).commit();
                break;
            case R.id.nav_report:
                getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_container,new FragmentInfo()).addToBackStack(tag).commit();
                break;
            case R.id.nav_predict:
                getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container,new FragmentPredict()).addToBackStack(tag).commit();
                break;
            case R.id.nav_test:
                getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container,new FragmentWarning()).addToBackStack(tag).commit();
                break;
            case R.id.nav_upload:
                getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container,new FragmentUpload()).addToBackStack(tag).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                sp.edit().putBoolean("logged",false).apply();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }  if (getSupportFragmentManager().getBackStackEntryCount() > 0 ) {
            getSupportFragmentManager().popBackStack("frag", 1); // Exclusive
            NavigationView navview = findViewById(R.id.nav_view);
            navview.setCheckedItem(R.id.navHome);

        }
        else
        {
           super.onBackPressed();
         //   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome(),"frag").commit();

        }
    }




}
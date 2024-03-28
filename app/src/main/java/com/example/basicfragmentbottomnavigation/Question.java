package com.example.basicfragmentbottomnavigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import es.dmoral.toasty.Toasty;

public class Question extends AppCompatActivity {

    static int []interest;

    public Button btn;
    private RadioButton radioButton;
    DocumentReference mDocRef;
    String type;
    int id1 =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        FragmentHome.flag = 1;
        btn=findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                next();

            }
        });

        String id="1";
        mDocRef = FirebaseFirestore.getInstance().collection("questions").document(id);


        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toasty.success(getApplicationContext(),"fetched!!",Toasty.LENGTH_SHORT).show();

                if(documentSnapshot.exists()){
                    String que = documentSnapshot.getString("que");
                    TextView textView = findViewById(R.id.textView4);
                    textView.setText(que);
                    RadioGroup radioGroup = (RadioGroup)findViewById(R.id.option);
                    String a = documentSnapshot.getString("a");
                    String b = documentSnapshot.getString("b");
                    String c = documentSnapshot.getString("c");
                    type = documentSnapshot.getString("que-type");



                    ((RadioButton) radioGroup.getChildAt(0)).setText(a);
                    ((RadioButton) radioGroup.getChildAt(1)).setText(b);
                    ((RadioButton) radioGroup.getChildAt(2)).setText(c);



                    interest =new int[5];
                    for(int i=0;i<5;i++ )
                    {
                        interest[i]=12;
                    }



                }
            }
        });


        // Capture the layout's TextView and set the string as its text



    }




    public void next(){
        id1++;
        String id=Integer.toString(id1);

        RadioGroup mRadioGroup;
        mRadioGroup = (RadioGroup) findViewById(R.id.option);

        int selectedId = mRadioGroup.getCheckedRadioButtonId();

        if(selectedId > 0 )
        {
            radioButton = (RadioButton) findViewById(selectedId);

            String text = radioButton.getText().toString();



            if(type.equals("arts"))
            {
                if(text.equals("like"))
                {
                    interest[0]+=2;

                }
                else if(text.equals("dislike"))
                {
                    interest[0]-=2;
                }
            }
            else if(type.equals("medical"))
            {
                if(text.equals("like"))
                {
                    interest[1]+=2;

                }
                else if(text.equals("dislike"))
                {
                    interest[1]-=2;
                }
            }
            else if(type.equals("management"))
            {
                if(text.equals("like"))
                {
                    interest[2]+=2;

                }
                else if(text.equals("dislike"))
                {
                    interest[2]-=2;
                }

            }
            else if(type.equals("computer"))
            {
                if(text.equals("like"))
                {
                    interest[3]+=2;

                }
                else if(text.equals("dislike"))
                {
                    interest[3]-=2;
                }
            }
            else if(type.equals("electronics"))
            {
                if(text.equals("like"))
                {
                    interest[4]+=2;

                }
                else if(text.equals("dislike"))
                {
                    interest[4]-=2;
                }

            }

            mRadioGroup.clearCheck();





            mDocRef = FirebaseFirestore.getInstance().collection("questions").document(id);


            mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    // Toasty.success(getApplicationContext(),"fetched!!",Toasty.LENGTH_SHORT).show();

                    if(documentSnapshot.exists()){
                        String que = documentSnapshot.getString("que");
                        TextView textView = findViewById(R.id.textView4);
                        textView.setText(que);
                        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.option);
                        String a = documentSnapshot.getString("a");
                        String b = documentSnapshot.getString("b");
                        String c = documentSnapshot.getString("c");
                        type = documentSnapshot.getString("que-type");



                        ((RadioButton) radioGroup.getChildAt(0)).setText(a);
                        ((RadioButton) radioGroup.getChildAt(1)).setText(b);
                        ((RadioButton) radioGroup.getChildAt(2)).setText(c);






                    }
                    else
                    {  Toasty.info(getApplicationContext(),"Completed!!",Toasty.LENGTH_SHORT).show();

                        Question.super.onBackPressed();
                    }


                }
            });



        }

        else
        {
            Toasty.info(getApplicationContext(),"PLEASE SELECT ONE OPTION!!",Toasty.LENGTH_SHORT).show();
        }




    }
}
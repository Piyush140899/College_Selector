package com.example.basicfragmentbottomnavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragmentPredict extends Fragment {
    static int marks;
    double board;
    View view;
    public Button btn;
    EditText mh,bb;
    String tag = "frag";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment_predict, container, false);
        // Inflate the layout for this fragment
        btn=view.findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fetch();

            }
        });




        return view;
    }

    public void fetch(){
        mh = (EditText) view.findViewById(R.id.editText2);
        bb = (EditText) view.findViewById(R.id.editText);

        if( mh.getText().toString().trim().length()==0||bb.getText().toString().trim().length()==0)
        {
            Toast.makeText(getActivity(),"Enter marks",Toast.LENGTH_SHORT).show();
        }
        else{
            String j= mh.getText().toString();
            marks = Integer.parseInt(j);
            String b=bb.getText().toString();
            board = Double.parseDouble(b);
            if(board <75)
            {
                Toast.makeText(getActivity(),"Not eligible!!",Toast.LENGTH_SHORT).show();

            }
            else {
                getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container, new FragmentPre()).addToBackStack(tag).commit();
            }
        }

    }


}

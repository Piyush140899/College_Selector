package com.example.basicfragmentbottomnavigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentWarning extends Fragment {
    View view;
    public Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_fragment_warning, container, false);

        btn=view.findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start();

            }
        });




        return view;
    }


public void start()
{
    Intent intent = new Intent(getActivity(), Question.class);
    startActivity(intent);


}



}

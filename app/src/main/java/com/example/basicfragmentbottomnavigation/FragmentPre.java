package com.example.basicfragmentbottomnavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentPre extends Fragment {
    private RecyclerView mRecylerView;
    private ImageAdapter mImgAdapter;
    private CollectionReference mDataRef;
    private ProgressBar mProgCircle;
    private List<Upload> mupload;
    private final FirebaseFirestore db= FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_pre, container, false);
        // Inflate the layout for this fragment
        mRecylerView=view.findViewById(R.id.recycle_view);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgCircle=view.findViewById(R.id.prg_circle);
        mupload=new ArrayList<>();
        mDataRef= FirebaseFirestore.getInstance().collection("college");


        db.collection("colleges").whereLessThanOrEqualTo("min",FragmentPredict.marks)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        // List<String> cities = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {

                            Upload upload=doc.toObject(Upload.class);

                            mupload.add(upload);


                        }
                        mImgAdapter=new ImageAdapter(getActivity(),mupload);
                        mRecylerView.setAdapter(mImgAdapter);
                        mProgCircle.setVisibility(View.INVISIBLE);
                        // Log.d(TAG, "Current cites in CA: " + cities);
                    }
                });





        return view;
        // Inflate the layout for this fragment

    }


}

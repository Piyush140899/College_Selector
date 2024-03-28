package com.example.basicfragmentbottomnavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class FragmentInfo extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_info, container, false);



        PieChart pieChart = (PieChart) view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        ArrayList<Entry> yvalues = new ArrayList<Entry>();

        if(FragmentHome.flag==0){
            yvalues.add(new Entry(20, 0));
            yvalues.add(new Entry(20, 1));
            yvalues.add(new Entry(20, 2));
            yvalues.add(new Entry(20, 3));
            yvalues.add(new Entry(20, 4));

        }
        else{
            yvalues.add(new Entry(Question.interest[0], 0));
            yvalues.add(new Entry(Question.interest[1], 1));
            yvalues.add(new Entry(Question.interest[2], 2));
            yvalues.add(new Entry(Question.interest[3], 3));
            yvalues.add(new Entry(Question.interest[4], 4));



        }



        PieDataSet dataSet = new PieDataSet(yvalues, "Interests");



        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Arts");
        xVals.add("Medical");
        xVals.add("Management");
        xVals.add("Computer");
        xVals.add("Electronics");
        pieChart.setDrawHoleEnabled(false);

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        PieData data = new PieData(xVals, dataSet);
        data.setValueTextSize(18f);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);

        return view;
    }


}

package com.msrit.abhilash.udbhavinsights;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.Date;

/**
 * Created by Abhilash on 05/02/2016.
 */
public class meets extends Fragment {

    public meets() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final CaldroidFragment mCaldroidFragment = new CaldroidFragment();

        View root = inflater.inflate(R.layout.fragment_meets, container, false);

        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(view.getContext(), date.toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getActivity(), text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                mCaldroidFragment.setBackgroundResourceForDate(R.drawable.cal_back,date);
                mCaldroidFragment.refreshView();

                Toast.makeText(getActivity(),
                        "Long click " + date.toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                Toast.makeText(getActivity(),
                        "Caldroid view is created",
                        Toast.LENGTH_SHORT).show();
            }
        };


        Bundle args = new Bundle();
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
        mCaldroidFragment.setArguments(args);
        mCaldroidFragment.setMinDateFromString("01-02-2016", "dd-MM-yyyy");
        mCaldroidFragment.setMaxDateFromString("31-04-2016", "dd-MM-yyyy");
        mCaldroidFragment.setCaldroidListener(listener);
        getActivity().getSupportFragmentManager().beginTransaction().replace( R.id.cal_container , mCaldroidFragment ).commit();

        return root;
    }
}

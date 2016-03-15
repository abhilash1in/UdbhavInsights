package com.msrit.abhilash.udbhavinsights;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by Abhilash on 14/03/2016.
 */
public class RegisterRootFragment extends Fragment {
    private static final String TAG = "RegisterRootFragment";
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.register_root_fragment, container, false);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);

        TextView tv = (TextView) view.findViewById(R.id.authMsg);

        ParseUser user = ParseUser.getCurrentUser();

        if(user!=null)
        {
            if(user.getBoolean("reg_auth")) {
                tv.setVisibility(View.GONE);
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.register_frame, new RegisterFragment());
                transaction.commit();

                /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Registration Type");
                builder.setPositiveButton("INTER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentTransaction transaction = getFragmentManager()
                                .beginTransaction();
                        transaction.replace(R.id.register_frame, new RegisterFragment());
                        transaction.commit();
                    }
                });
                builder.setNegativeButton("INTRA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentTransaction transaction = getFragmentManager()
                                .beginTransaction();
                        transaction.replace(R.id.register_frame, new IntraRegisterFragment());
                        transaction.commit();
                    }
                });
                builder.setCancelable(false);
                builder.show();*/


            }
        }

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mRefreshLayout.setRefreshing(false);
                startActivity(intent);
            }
        });

        return view;
    }
}

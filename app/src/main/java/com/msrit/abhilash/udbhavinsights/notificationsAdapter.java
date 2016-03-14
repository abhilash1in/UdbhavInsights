package com.msrit.abhilash.udbhavinsights;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Abhilash on 08/02/2016.
 */
public class notificationsAdapter extends RecyclerView.Adapter<notificationsAdapter.ViewHolder> {

    private List<notificationItem> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView content;


        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.notifTitle);
            content = (TextView) v.findViewById(R.id.notifContent);
        }
    }

    public notificationsAdapter(List<notificationItem> myDataset) {
        mDataset=myDataset;
        Log.v("test adapter",myDataset.size()+"");
    }

    public notificationsAdapter() {
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(mDataset.get(position).getTitle());
        holder.content.setText(mDataset.get(position).getContent());

    }

    @Override
    public notificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notif_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

package com.msrit.abhilash.udbhavinsights.TakeAttendance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msrit.abhilash.udbhavinsights.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhilash on 05/02/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<itemData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView number;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.rowName);
            number = (TextView) v.findViewById(R.id.rowPhone);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<itemData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(mDataset.get(position).getName());
        holder.number.setText(mDataset.get(position).getPhone());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void add(int position, itemData item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(itemData item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public itemData removeItem(int position) {
        final itemData model = mDataset.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void move(int fromPosition, int toPosition)
    {
        final itemData model = mDataset.remove(fromPosition);
        mDataset.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }


    public void animateTo(List<itemData> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<itemData> newModels) {
        for (int i = mDataset.size() - 1; i >= 0; i--) {
            final itemData model = mDataset.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<itemData> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final itemData model = newModels.get(i);
            if (!mDataset.contains(model)) {
                add(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<itemData> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final itemData model = newModels.get(toPosition);
            final int fromPosition = mDataset.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                move(fromPosition, toPosition);
            }
        }
    }

}
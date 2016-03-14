package com.msrit.abhilash.udbhavinsights.TakeAttendance;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.msrit.abhilash.udbhavinsights.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Abhilash on 05/02/2016.
 */
public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {
    public List<itemData> mDataset;
    Context mContext;
    SharedPreferences prefs;
    final Calendar c = Calendar.getInstance();
    JSONObject s;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView number;
        public ImageView tick,cross;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.rowName);
            number = (TextView) v.findViewById(R.id.rowPhone);
            tick = (ImageView) v.findViewById(R.id.tick);
            cross = (ImageView) v.findViewById(R.id.cross);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter2(Context context, List<itemData> myDataset , JSONObject j) {
        mDataset = myDataset;
        mContext = context;
        s=j;
        try
        {
            Log.v("test","constructor with json parameter, init = "+s.getInt("init"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public MyAdapter2(Context context, List<itemData> myDataset) {
        mDataset = myDataset;
        mContext = context;
        s = new JSONObject();
        try{
            s.put("init",0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.v("test","constructor WITHOUT json parameter");
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);

        /*prefs = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        Log.v("date local",prefs.getInt("date",0)+"");
        Log.v("date local",prefs.getInt("month",0)+"");
        Log.v("date local",prefs.getInt("year",0)+"");

        if((prefs.getInt("date",0)==c.get(Calendar.DAY_OF_MONTH)&&(prefs.getInt("month",0)==c.get(Calendar.MONTH))&&(prefs.getInt("year",0)==c.get(Calendar.YEAR))))
        {
            //do nothing
            vh.tick.setEnabled(false);
            vh.cross.setVisibility(View.INVISIBLE);
            Log.v("date 123","found");
        }
        else
        {
            vh.tick.setVisibility(View.VISIBLE);
            vh.cross.setVisibility(View.VISIBLE);
            vh.tick.setEnabled(true);
            vh.cross.setEnabled(false);
            Log.v("date 123", "not found");
        }*/

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        /*prefs = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final Calendar c = Calendar.getInstance();

        Log.v("date local",prefs.getInt("date",0)+"");
        Log.v("date local",prefs.getInt("month",0)+"");
        Log.v("date local",prefs.getInt("year",0)+"");

        if((prefs.getInt("date",0)==c.get(Calendar.DAY_OF_MONTH)&&(prefs.getInt("month",0)==c.get(Calendar.MONTH))&&(prefs.getInt("year",0)==c.get(Calendar.YEAR))))
        {
            //do nothing
            holder.tick.setEnabled(false);
            holder.cross.setVisibility(View.INVISIBLE);
            Log.v("date 123","found");
        }
        else
        {
            holder.tick.setVisibility(View.VISIBLE);
            holder.cross.setVisibility(View.VISIBLE);
            holder.tick.setEnabled(true);
            holder.cross.setEnabled(false);
            Log.v("date 123", "not found");
        }*/

        holder.name.setText(mDataset.get(position).getName());
        holder.number.setText(mDataset.get(position).getPhone());
        /*Log.v("test",(mDataset.get(position).getPhone()));*/
        int temp;
        try{
            temp =s.getInt("init");
        }
        catch (Exception e)
        {
            temp = -1;
            e.printStackTrace();
        }
        if(temp!=0)
        {
            Log.v("test","temp= "+temp);
            try
            {
                Log.v("test status",s.get(position+"").toString());
                if(s.get(position+"").toString().equals("true"))
                {
                    Log.v("test","true");
                    holder.tick.setEnabled(false);
                    holder.cross.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Log.v("test","true");
                }
            }
            catch (Exception e)
            {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v("test", e.getMessage());
                e.printStackTrace();
            }
        }
        else
        {
            Log.v("test","temp= "+temp);
        }
        holder.tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    s.put(position+"",true);
                    prefs = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("status",s.toString());
                    editor.putInt("init", 1);
                    editor.putInt("editedOnDate",c.get(Calendar.DAY_OF_MONTH));
                    editor.apply();
                }
                catch (Exception e)
                {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.v("test", e.getMessage());
                    e.printStackTrace();
                }

                /*Log.v("date1", c.get(Calendar.DAY_OF_MONTH)+"");
                Log.v("date1", c.get(Calendar.MONTH)+ "");
                Log.v("date1", c.get(Calendar.YEAR) + "");

                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("date", c.get(Calendar.DAY_OF_MONTH));
                editor.putInt("month", c.get(Calendar.MONTH));
                editor.putInt("year", c.get(Calendar.YEAR));
                editor.apply();

                Log.v("date2", prefs.getInt("date", 0)+"");
                Log.v("date2", prefs.getInt("month", 0)+"");
                Log.v("date2", prefs.getInt("year",0)+"");*/

                try
                {
                    JSONObject temp = new JSONObject();
                    temp.put("day",c.get(Calendar.DAY_OF_MONTH));
                    temp.put("month",c.get(Calendar.MONTH));
                    temp.put("year",c.get(Calendar.YEAR));

                    FetchData.result.get(position).addUnique("values", temp.toString());
                    FetchData.result.get(position).increment("attended");


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                FetchData.display(mContext);

                holder.tick.setEnabled(false);
                holder.cross.setVisibility(View.INVISIBLE);
            }
        });
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

    public void setFilter(List<itemData> models) {
        mDataset = new ArrayList<>();
        mDataset.addAll(models);
        notifyDataSetChanged();
    }

}
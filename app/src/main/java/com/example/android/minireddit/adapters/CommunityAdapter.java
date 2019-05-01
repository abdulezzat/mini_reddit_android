package com.example.android.minireddit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.datastructure.Community;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aly on 5/1/2019.
 */

public class CommunityAdapter extends BaseAdapter implements Filterable {
    private ArrayList<Community> mOriginalValues; // Original Values
    private ArrayList<Community> mDisplayedValues;
    LayoutInflater inflater;// Values to be displayed

    public CommunityAdapter(Context context, ArrayList<Community> mProductArrayList) {
        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if (ListItemView == null) {//for making new List_item if there is no main one to change its data
            ListItemView = inflater.inflate(R.layout.post_list_item, parent, false);
        }
        final Community currentCommunity =  mDisplayedValues.get(position);// getting the current word in the arraylist
        TextView comm_name=(TextView) ListItemView.findViewById(R.id.comm_name);
        comm_name.setText(currentCommunity.getCommunityName());
        ImageView comm_photo =(ImageView)ListItemView.findViewById(R.id.comm_photo);
        return ListItemView;

    }
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<Community>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Community> FilteredArrList = new ArrayList<Community>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Community>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getCommunityName();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(mOriginalValues.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

}

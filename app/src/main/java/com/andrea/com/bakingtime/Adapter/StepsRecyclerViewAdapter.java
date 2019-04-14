package com.andrea.com.bakingtime.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrea.com.bakingtime.Fragment.StepsFragment.OnListFragmentInteractionListener;
import com.andrea.com.bakingtime.Model.Steps;
import com.andrea.com.bakingtime.R;


/**
 * {@link RecyclerView.Adapter} that can display a {@link Steps} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.ViewHolder> {

    private final Steps[] mData;
    private final OnListFragmentInteractionListener mListener;

    public StepsRecyclerViewAdapter(Steps[] items, OnListFragmentInteractionListener listener) {
        mData = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_steps_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindViewHolder(mData[position]);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mData == null){return 0;}
        return mData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.item_steps);
        }

        void bindViewHolder (Steps step){
            mContentView.setText(step.getDescription());
        }
    }
}

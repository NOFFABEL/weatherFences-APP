package com.example.entity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.WeatherHisto.OnListFragmentInteractionListener;
import com.example.entity.dummy.DummyContent.DummyItem;
import com.umons.fpms.model.WeatherDB;
import com.umons.fpms.model.WeatherHttpClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder> {

    private final List<WeatherDB> mValues;
    private final OnListFragmentInteractionListener mListener;

    public WeatherRecyclerViewAdapter(List<WeatherDB> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_histo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txt_temp_h.setText(String.format("%s", mValues.get(position).getTemp()));
        holder.txt_loc_h.setText(String.format("%s, %s", mValues.get(position).getCity(), mValues.get(position).getCountry()));
        holder.txt_date_h.setText( mValues.get(position).getDate_in());
        // TODO: Set the bitmap icon here.
        //holder.mImg.setImageBitmap(mValues.get(position).getIcon());

        /*holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImg;
        public final TextView txt_temp_h, txt_date_h, txt_loc_h;
        public WeatherDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImg = (ImageView) view.findViewById(R.id.img_weather_h);
            txt_temp_h = (TextView) view.findViewById(R.id.lbl_temp_h);
            txt_date_h = (TextView) view.findViewById(R.id.lbl_date_h);
            txt_loc_h = (TextView) view.findViewById(R.id.lbl_loc_h);
        }

    }
}

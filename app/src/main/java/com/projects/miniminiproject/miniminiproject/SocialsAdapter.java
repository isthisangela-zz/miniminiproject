package com.projects.miniminiproject.miniminiproject;

/**
 * Created by Aayush on 10/3/2016.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Aayush on 9/28/2016.
 */

public class SocialsAdapter extends RecyclerView.Adapter<SocialsAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Utils.Social> socialist;

    public SocialsAdapter(Context context, ArrayList<Utils.Social> socialist) {
        this.context = context;
        this.socialist = socialist;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.socials_row_view, parent, false);
        return new CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        Utils.Social social = socialist.get(position);
        holder.eventNameTextView.setText(social.getName());
        holder.creatorTextView.setText(social.getCreator());
        Utils.setBitmap(context, holder.cardView, holder.imageView.getId(), social.getID());
    }


    @Override
    public int getItemCount() {
        return socialist.size();
    }

    /**
     * A card displayed in the RecyclerView
     */
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventNameTextView;
        ImageView imageView;
        TextView creatorTextView;
        CardView cardView;

        public CustomViewHolder (View view) {
            super(view);
            this.eventNameTextView = (TextView) view.findViewById(R.id.eventNameTextView);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.creatorTextView = (TextView) view.findViewById(R.id.creatorTextView);
            this.cardView = (CardView) view.findViewById(R.id.card);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}

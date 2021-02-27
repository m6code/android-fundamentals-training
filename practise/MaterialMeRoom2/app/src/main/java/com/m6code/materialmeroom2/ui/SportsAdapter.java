package com.m6code.materialmeroom2.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.m6code.materialmeroom2.R;
import com.m6code.materialmeroom2.model.Sport;

import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportViewHolder> {

    String TAG = SportsAdapter.class.getSimpleName();

    private List<Sport> mSportsData;
    private Context mContext;
    private final LayoutInflater mInflater;

    public SportsAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SportViewHolder(mInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SportViewHolder holder, int position) {
        Sport currentSport = mSportsData.get(position);
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData == null ? 0 : mSportsData.size();
    }

    void setSportsData(List<Sport> sports){
        mSportsData = sports;
        notifyDataSetChanged();
    }

    // Get the Sport at a position
    public Sport getSportAtPosition(int pos){
        return mSportsData.get(pos);
    }

    public class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected ImageView mImageView;
        protected TextView mTitle;
        protected TextView mInfo;

        public SportViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.sportsImage);
            mTitle = itemView.findViewById(R.id.title);
            mInfo = itemView.findViewById(R.id.subTitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Sport currentSport = mSportsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource",
                    mContext.getResources().getIdentifier(currentSport.getImageResource(),
                            "drawable", mContext.getPackageName()));
            mContext.startActivity(detailIntent);
        }

        void bindTo(Sport currentSport){
            mTitle.setText(currentSport.getTitle());
            mInfo.setText(currentSport.getInfo());

            String imgRsr = currentSport.getImageResource();
            // Gets the image resource id
            int res = mContext.getResources().getIdentifier(imgRsr, "drawable", mContext.getPackageName());

            Glide.with(mContext)
                    .load(res)
                    .into(mImageView);
        }
    }
}

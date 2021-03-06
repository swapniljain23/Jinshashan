package com.swapniljain.jinshashan.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.model.JNListDataModel;

import java.util.List;
import java.util.Locale;

public class JNListAdapter extends  RecyclerView.Adapter<JNListAdapter.JNListViewHolder> {

    private String TAG = JNListAdapter.class.toString();
    private final List<JNListDataModel> mDataModels;
    private Context mContext;

    public final CardViewClickListener mCardViewClickListener;

    public JNListAdapter(List<JNListDataModel> dataModels, CardViewClickListener listener) {
        mDataModels = dataModels;
        mCardViewClickListener = listener;
    }

    @NonNull
    @Override
    public JNListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutIdJNList = R.layout.list_item_jnlist;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdJNList, parent, shouldAttachToParentImmediately);
        JNListAdapter.JNListViewHolder viewHolder = new JNListAdapter.JNListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JNListViewHolder holder, int position) {
        JNListDataModel dataModel = mDataModels.get(position);

        // Set the data.
        holder.card_title.setText(dataModel.dikshaInfo.dikshaName);
        holder.card_subtitle_1.setText(String.format(Locale.US,
                "%s, %d Years",
                dataModel.sect.sect1,
                dataModel.personalInfo.age));
//        holder.card_subtitle_2.setText(String.format("%s, %s",
//                dataModel.recentInfo.city,
//                dataModel.recentInfo.state));
        holder.card_subtitle_2.setText(dataModel.recentInfo.address);
        String photoURL = dataModel.getPhotoURL();

        // Default placeholder image.
        int placeholderImageID;
        if (dataModel.personalInfo.gender.equalsIgnoreCase("male")) {
            placeholderImageID = R.drawable.hero_image;
        } else {
            placeholderImageID = R.drawable.default_sadhvi_image;
        }

        if (TextUtils.isEmpty(photoURL)) {
            Picasso.get()
                    .load(placeholderImageID)
                    .into(holder.card_image_view);
        } else {
            Picasso.get()
                    .load(photoURL)
                    .placeholder(placeholderImageID)
                    .into(holder.card_image_view);
        }
    }

    @Override
    public int getItemCount() {
        return mDataModels.size();
    }

    public interface CardViewClickListener {
        void onCardViewClick(int clickedCardItemPosition);
    }

    // View holder.

    class JNListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView card_title;
        private final TextView card_subtitle_1;
        private final TextView card_subtitle_2;
        private final ImageView card_image_view;

        public JNListViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            card_title = view.findViewById(R.id.tv_card_title);
            card_subtitle_1 = view.findViewById(R.id.tv_card_subtitle1);
            card_subtitle_2 = view.findViewById(R.id.tv_card_subtitle2);
            card_image_view = view.findViewById(R.id.iv_card_image);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mCardViewClickListener.onCardViewClick(clickedPosition);
        }
    }
}

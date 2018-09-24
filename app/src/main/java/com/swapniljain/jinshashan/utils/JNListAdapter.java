package com.swapniljain.jinshashan.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.model.JNListDataModel;

import java.util.List;

public class JNListAdapter extends  RecyclerView.Adapter<JNListAdapter.JNListViewHolder> {

    private List<JNListDataModel> mDataModels;
    private Context mContext;

    public CardViewClickListener mCardViewClickListener;

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
        holder.card_title.setText(dataModel.specialRemarks);
        holder.card_subtitle.setText(mContext.getResources().getString(R.string.default_text));
        holder.card_image.setImageResource(R.drawable.image_placeholder);
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

        private TextView card_title;
        private TextView card_subtitle;
        private ImageView card_image;

        public JNListViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            card_title = view.findViewById(R.id.tv_card_title);
            card_subtitle = view.findViewById(R.id.tv_card_subtitle);
            card_image = view.findViewById(R.id.iv_card_image);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mCardViewClickListener.onCardViewClick(clickedPosition);
        }
    }
}
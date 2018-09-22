package com.swapniljain.jinshashan.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.model.JNListDataModel;

import java.util.List;

public class JNListAdapter extends  RecyclerView.Adapter<JNListAdapter.JNListViewHolder> {

        private List<JNListDataModel> mDataModels;

        public JNListAdapter(List<JNListDataModel> dataModels) {
            mDataModels = dataModels;
        }

        @NonNull
        @Override
        public JNListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            int layoutIdJNList = R.layout.list_item_jnlist;
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdJNList, parent, shouldAttachToParentImmediately);
            JNListAdapter.JNListViewHolder viewHolder = new JNListAdapter.JNListViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull JNListViewHolder holder, int position) {
            JNListDataModel dataModel = mDataModels.get(position);
            holder.card_title.setText(dataModel.specialRemarks);
        }

        @Override
        public int getItemCount() {
            return mDataModels.size();
        }

        // View holder.
        class JNListViewHolder extends RecyclerView.ViewHolder {

            private TextView card_title;

            public JNListViewHolder(View view) {
                super(view);
                card_title = (TextView)view.findViewById(R.id.tv_card_title);
            }
        }
}

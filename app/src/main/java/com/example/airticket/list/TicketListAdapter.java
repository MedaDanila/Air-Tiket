package com.example.airticket.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airticket.R;

import java.util.ArrayList;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.TicketListViewHolder> {
    private Context mContext;
    private ArrayList<TicketListItem> mExampleList;

    public TicketListAdapter(Context context, ArrayList<TicketListItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public TicketListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new TicketListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketListViewHolder holder, int position) {
        TicketListItem currentItem = mExampleList.get(position);
        String from = currentItem.getmFrom();
        String dest = currentItem.getmDest();
        String date = currentItem.getmDate();
        int cost = currentItem.mCost();
        holder.mTextViewFrom.setText("Откуда:" + from);
        holder.mTextViewDest.setText("Куда:" + dest);
        holder.mTextViewDate.setText("Дата:" + date);
        holder.mTextViewCost.setText("Стоимость:" + cost);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class TicketListViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewFrom;
        public TextView mTextViewDest;
        public TextView mTextViewDate;
        public TextView mTextViewCost;

        public TicketListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewFrom = itemView.findViewById(R.id.text_view_from);
            mTextViewDest = itemView.findViewById(R.id.text_view_dest);
            mTextViewDate = itemView.findViewById(R.id.text_view_date);
            mTextViewCost = itemView.findViewById(R.id.text_view_cost);
        }
    }
}

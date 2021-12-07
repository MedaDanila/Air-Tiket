package com.example.airticket.list;

public class TicketListItem {
    private String mFrom;
    private String mDest;
    private String mDate;
    private int mCost;

    public TicketListItem(String from, String dest, String date, int cost) {
        mFrom = from;
        mDest = dest;
        mDate = date;
        mCost = cost;
    }

    public String getmFrom() {
        return mFrom;
    }

    public String getmDest() {
        return mDest;
    }

    public String getmDate() {
        return mDate;
    }

    public int mCost() {
        return mCost;
    }
}

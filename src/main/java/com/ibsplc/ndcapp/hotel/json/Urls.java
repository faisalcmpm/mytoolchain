package com.ibsplc.ndcapp.hotel.json;

public class Urls
{
    private String hotel_details;

    public String getHotel_details ()
    {
        return hotel_details;
    }

    public void setHotel_details (String hotel_details)
    {
        this.hotel_details = hotel_details;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [hotel_details = "+hotel_details+"]";
    }
}
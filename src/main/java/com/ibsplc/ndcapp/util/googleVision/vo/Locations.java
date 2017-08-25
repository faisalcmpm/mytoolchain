package com.ibsplc.ndcapp.util.googleVision.vo;
public class Locations
{
    private LatLng latLng;

    public LatLng getLatLng ()
    {
        return latLng;
    }

    public void setLatLng (LatLng latLng)
    {
        this.latLng = latLng;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [latLng = "+latLng+"]";
    }
}
			
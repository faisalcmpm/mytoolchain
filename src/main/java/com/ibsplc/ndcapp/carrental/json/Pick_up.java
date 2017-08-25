package com.ibsplc.ndcapp.carrental.json;

public class Pick_up
{
   
	private String address;

    private String distance_to_search_location_in_km;

    private String[] geo_info;

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getDistance_to_search_location_in_km ()
    {
        return distance_to_search_location_in_km;
    }

    public void setDistance_to_search_location_in_km (String distance_to_search_location_in_km)
    {
        this.distance_to_search_location_in_km = distance_to_search_location_in_km;
    }

    public String[] getGeo_info ()
    {
        return geo_info;
    }

    public void setGeo_info (String[] geo_info)
    {
        this.geo_info = geo_info;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [address = "+address+", distance_to_search_location_in_km = "+distance_to_search_location_in_km+", geo_info = "+geo_info+"]";
    }
}
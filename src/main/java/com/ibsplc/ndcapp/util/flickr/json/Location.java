package com.ibsplc.ndcapp.util.flickr.json;

public class Location
{
    private Region region;

    private String place_id;

    private String woeid;

    private String context;

    private Locality locality;

    private String longitude;

    private String latitude;

    private String accuracy;

    private Country country;

    public Region getRegion ()
    {
        return region;
    }

    public void setRegion (Region region)
    {
        this.region = region;
    }

    public String getPlace_id ()
    {
        return place_id;
    }

    public void setPlace_id (String place_id)
    {
        this.place_id = place_id;
    }

    public String getWoeid ()
    {
        return woeid;
    }

    public void setWoeid (String woeid)
    {
        this.woeid = woeid;
    }

    public String getContext ()
    {
        return context;
    }

    public void setContext (String context)
    {
        this.context = context;
    }

    public Locality getLocality ()
    {
        return locality;
    }

    public void setLocality (Locality locality)
    {
        this.locality = locality;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getAccuracy ()
    {
        return accuracy;
    }

    public void setAccuracy (String accuracy)
    {
        this.accuracy = accuracy;
    }

    public Country getCountry ()
    {
        return country;
    }

    public void setCountry (Country country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [region = "+region+", place_id = "+place_id+", woeid = "+woeid+", context = "+context+", locality = "+locality+", longitude = "+longitude+", latitude = "+latitude+", accuracy = "+accuracy+", country = "+country+"]";
    }
}
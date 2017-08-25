package com.ibsplc.ndcapp.util.googleVision.vo;
public class LandmarkAnnotations
{
    private Locations[] locations;

    private String description;

    private String score;

    private BoundingPoly boundingPoly;

    private String mid;

    public Locations[] getLocations ()
    {
        return locations;
    }

    public void setLocations (Locations[] locations)
    {
        this.locations = locations;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    public BoundingPoly getBoundingPoly ()
    {
        return boundingPoly;
    }

    public void setBoundingPoly (BoundingPoly boundingPoly)
    {
        this.boundingPoly = boundingPoly;
    }

    public String getMid ()
    {
        return mid;
    }

    public void setMid (String mid)
    {
        this.mid = mid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [locations = "+locations+", description = "+description+", score = "+score+", boundingPoly = "+boundingPoly+", mid = "+mid+"]";
    }
}
			
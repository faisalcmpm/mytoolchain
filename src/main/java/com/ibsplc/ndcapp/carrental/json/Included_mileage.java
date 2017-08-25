package com.ibsplc.ndcapp.carrental.json;

public class Included_mileage
{
    
	private String unlimited;

    public String getUnlimited ()
    {
        return unlimited;
    }

    public void setUnlimited (String unlimited)
    {
        this.unlimited = unlimited;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [unlimited = "+unlimited+"]";
    }
}
package com.ibsplc.ndcapp.carrental.json;

public class Location
{
    
	private Pick_up pick_up;

    public Pick_up getPick_up ()
    {
        return pick_up;
    }

    public void setPick_up (Pick_up pick_up)
    {
        this.pick_up = pick_up;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pick_up = "+pick_up+"]";
    }
}
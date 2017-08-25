package com.ibsplc.ndcapp.carrental.json;

public class Fuel
{
  
	private String policy;

    private String type;

    private String fair;

    public String getPolicy ()
    {
        return policy;
    }

    public void setPolicy (String policy)
    {
        this.policy = policy;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getFair ()
    {
        return fair;
    }

    public void setFair (String fair)
    {
        this.fair = fair;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [policy = "+policy+", type = "+type+", fair = "+fair+"]";
    }
}
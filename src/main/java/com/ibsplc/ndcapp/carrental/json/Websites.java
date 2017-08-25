package com.ibsplc.ndcapp.carrental.json;

public class Websites
{
   	private String id;

    private String optimised_for_mobile;

    private String image_url;

    private String in_progress;

    private String name;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getOptimised_for_mobile ()
    {
        return optimised_for_mobile;
    }

    public void setOptimised_for_mobile (String optimised_for_mobile)
    {
        this.optimised_for_mobile = optimised_for_mobile;
    }

    public String getImage_url ()
    {
        return image_url;
    }

    public void setImage_url (String image_url)
    {
        this.image_url = image_url;
    }

    public String getIn_progress ()
    {
        return in_progress;
    }

    public void setIn_progress (String in_progress)
    {
        this.in_progress = in_progress;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", optimised_for_mobile = "+optimised_for_mobile+", image_url = "+image_url+", in_progress = "+in_progress+", name = "+name+"]";
    }
}
	
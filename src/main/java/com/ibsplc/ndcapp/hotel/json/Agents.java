package com.ibsplc.ndcapp.hotel.json;

public class Agents
{
    private String id;

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
        return "ClassPojo [id = "+id+", image_url = "+image_url+", in_progress = "+in_progress+", name = "+name+"]";
    }
}
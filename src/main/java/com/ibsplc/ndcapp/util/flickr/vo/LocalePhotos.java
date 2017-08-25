package com.ibsplc.ndcapp.util.flickr.vo;

import com.ibsplc.ndcapp.util.flickr.json.Photos;

public class LocalePhotos
{
    private Photos photos;

    private String stat;

    public Photos getPhotos ()
    {
        return photos;
    }

    public void setPhotos (Photos photos)
    {
        this.photos = photos;
    }

    public String getStat ()
    {
        return stat;
    }

    public void setStat (String stat)
    {
        this.stat = stat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [photos = "+photos+", stat = "+stat+"]";
    }
}
			
			
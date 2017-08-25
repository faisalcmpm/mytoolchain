
package com.ibsplc.ndcapp.util.flickr.json;

public class Locality
{
    private String place_id;

    private String _content;

    private String woeid;

    public String getPlace_id ()
    {
        return place_id;
    }

    public void setPlace_id (String place_id)
    {
        this.place_id = place_id;
    }

    public String get_content ()
    {
        return _content;
    }

    public void set_content (String _content)
    {
        this._content = _content;
    }

    public String getWoeid ()
    {
        return woeid;
    }

    public void setWoeid (String woeid)
    {
        this.woeid = woeid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [place_id = "+place_id+", _content = "+_content+", woeid = "+woeid+"]";
    }
}
			
			
package com.ibsplc.ndcapp.airport;
public class Airports
{
    private String timezone;

    private String terminal;

    private String iatacode;

    private String name;

    private String icaocode;

    private String lng;

    private String lat;

    private String gate;

    private String country;

    private String city;

    public String getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (String timezone)
    {
        this.timezone = timezone;
    }

    public String getTerminal ()
    {
        return terminal;
    }

    public void setTerminal (String terminal)
    {
        this.terminal = terminal;
    }

    public String getIatacode ()
    {
        return iatacode;
    }

    public void setIatacode (String iatacode)
    {
        this.iatacode = iatacode;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getIcaocode ()
    {
        return icaocode;
    }

    public void setIcaocode (String icaocode)
    {
        this.icaocode = icaocode;
    }

    public String getLng ()
    {
        return lng;
    }

    public void setLng (String lng)
    {
        this.lng = lng;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }
    public String getGate ()
    {
        return gate;
    }

    public void setGate (String gate)
    {
        this.gate = gate;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timezone = "+timezone+", terminal = "+terminal+", iatacode = "+iatacode+", name = "+name+", icaocode = "+icaocode+", lng = "+lng+", lat = "+lat+", gate = "+gate+", country = "+country+", city = "+city+"]";
    }
}
			
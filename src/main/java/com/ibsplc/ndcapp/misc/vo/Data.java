package com.ibsplc.ndcapp.misc.vo;
public class Data
{
    private String id;

    private String last_year_kilometers;

    private String email;

    private String hours;

    private Airlines[] airlines;

    private String name;

    private String kilometers;

    private String last_year_hours;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLast_year_kilometers ()
    {
        return last_year_kilometers;
    }

    public void setLast_year_kilometers (String last_year_kilometers)
    {
        this.last_year_kilometers = last_year_kilometers;
    }

 

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

   

    public String getHours ()
    {
        return hours;
    }

    public void setHours (String hours)
    {
        this.hours = hours;
    }

    public Airlines[] getAirlines ()
    {
        return airlines;
    }

    public void setAirlines (Airlines[] airlines)
    {
        this.airlines = airlines;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getKilometers ()
    {
        return kilometers;
    }

    public void setKilometers (String kilometers)
    {
        this.kilometers = kilometers;
    }

    public String getLast_year_hours ()
    {
        return last_year_hours;
    }

    public void setLast_year_hours (String last_year_hours)
    {
        this.last_year_hours = last_year_hours;
    }

   
    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", last_year_kilometers = "+last_year_kilometers+", email = "+email+", hours = "+hours+", airlines = "+airlines+", name = "+name+", kilometers = "+kilometers+", last_year_hours = "+last_year_hours+"]";
    }
}
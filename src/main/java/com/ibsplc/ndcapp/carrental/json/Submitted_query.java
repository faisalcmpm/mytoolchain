package com.ibsplc.ndcapp.carrental.json;

public class Submitted_query
{
   
	private String drop_off_date_time;

    private String market;

    private String pickup_date_time;

    private String driver_age;

    private String pickup_place;

    private String locale;

    private String dropoff_place;

    private String currency;

    public String getDrop_off_date_time ()
    {
        return drop_off_date_time;
    }

    public void setDrop_off_date_time (String drop_off_date_time)
    {
        this.drop_off_date_time = drop_off_date_time;
    }

    public String getMarket ()
    {
        return market;
    }

    public void setMarket (String market)
    {
        this.market = market;
    }

    public String getPickup_date_time ()
    {
        return pickup_date_time;
    }

    public void setPickup_date_time (String pickup_date_time)
    {
        this.pickup_date_time = pickup_date_time;
    }

    public String getDriver_age ()
    {
        return driver_age;
    }

    public void setDriver_age (String driver_age)
    {
        this.driver_age = driver_age;
    }

    public String getPickup_place ()
    {
        return pickup_place;
    }

    public void setPickup_place (String pickup_place)
    {
        this.pickup_place = pickup_place;
    }

    public String getLocale ()
    {
        return locale;
    }

    public void setLocale (String locale)
    {
        this.locale = locale;
    }

    public String getDropoff_place ()
    {
        return dropoff_place;
    }

    public void setDropoff_place (String dropoff_place)
    {
        this.dropoff_place = dropoff_place;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [drop_off_date_time = "+drop_off_date_time+", market = "+market+", pickup_date_time = "+pickup_date_time+", driver_age = "+driver_age+", pickup_place = "+pickup_place+", locale = "+locale+", dropoff_place = "+dropoff_place+", currency = "+currency+"]";
    }
}
	
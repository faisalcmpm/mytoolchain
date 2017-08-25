package com.ibsplc.ndcapp.carrental.json;

public class Cars
{
  
	private String doors;

    private Location location;

    private String vehicle;

    private String price_all_days;

    private String car_class_id;

    private String sipp;

    private String image_id;

    private String manual;

    private String deeplink_url;

    private String mandatory_chauffeur;

    private String air_conditioning;

    private String seats;

    private Value_add value_add;

    private String bags;

    private String website_id;

    public String getDoors ()
    {
        return doors;
    }

    public void setDoors (String doors)
    {
        this.doors = doors;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getVehicle ()
    {
        return vehicle;
    }

    public void setVehicle (String vehicle)
    {
        this.vehicle = vehicle;
    }

    public String getPrice_all_days ()
    {
        return price_all_days;
    }

    public void setPrice_all_days (String price_all_days)
    {
        this.price_all_days = price_all_days;
    }

    public String getCar_class_id ()
    {
        return car_class_id;
    }

    public void setCar_class_id (String car_class_id)
    {
        this.car_class_id = car_class_id;
    }

    public String getSipp ()
    {
        return sipp;
    }

    public void setSipp (String sipp)
    {
        this.sipp = sipp;
    }

    public String getImage_id ()
    {
        return image_id;
    }

    public void setImage_id (String image_id)
    {
        this.image_id = image_id;
    }

    public String getManual ()
    {
        return manual;
    }

    public void setManual (String manual)
    {
        this.manual = manual;
    }

    public String getDeeplink_url ()
    {
        return deeplink_url;
    }

    public void setDeeplink_url (String deeplink_url)
    {
        this.deeplink_url = deeplink_url;
    }

    public String getMandatory_chauffeur ()
    {
        return mandatory_chauffeur;
    }

    public void setMandatory_chauffeur (String mandatory_chauffeur)
    {
        this.mandatory_chauffeur = mandatory_chauffeur;
    }

    public String getAir_conditioning ()
    {
        return air_conditioning;
    }

    public void setAir_conditioning (String air_conditioning)
    {
        this.air_conditioning = air_conditioning;
    }

    public String getSeats ()
    {
        return seats;
    }

    public void setSeats (String seats)
    {
        this.seats = seats;
    }

    public Value_add getValue_add ()
    {
        return value_add;
    }

    public void setValue_add (Value_add value_add)
    {
        this.value_add = value_add;
    }

    public String getBags ()
    {
        return bags;
    }

    public void setBags (String bags)
    {
        this.bags = bags;
    }

    public String getWebsite_id ()
    {
        return website_id;
    }

    public void setWebsite_id (String website_id)
    {
        this.website_id = website_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [doors = "+doors+", location = "+location+", vehicle = "+vehicle+", price_all_days = "+price_all_days+", car_class_id = "+car_class_id+", sipp = "+sipp+", image_id = "+image_id+", manual = "+manual+", deeplink_url = "+deeplink_url+", mandatory_chauffeur = "+mandatory_chauffeur+", air_conditioning = "+air_conditioning+", seats = "+seats+", value_add = "+value_add+", bags = "+bags+", website_id = "+website_id+"]";
    }
}
	
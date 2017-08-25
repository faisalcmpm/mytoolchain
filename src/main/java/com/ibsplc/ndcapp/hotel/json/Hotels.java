package com.ibsplc.ndcapp.hotel.json;

public class Hotels
{
    private String distance_from_search;

    private String hotel_id;

    private String[] amenities;

    private String tag;

    private String score;

    private String popularity_desc;

    private String address;

    private String name;

    private String longitude;

    private String latitude;

    private String[] types;

    private String district;

    private String star_rating;

    private String number_of_rooms;

    private String popularity;

    public String getDistance_from_search ()
    {
        return distance_from_search;
    }

    public void setDistance_from_search (String distance_from_search)
    {
        this.distance_from_search = distance_from_search;
    }

    public String getHotel_id ()
    {
        return hotel_id;
    }

    public void setHotel_id (String hotel_id)
    {
        this.hotel_id = hotel_id;
    }

    public String[] getAmenities ()
    {
        return amenities;
    }

    public void setAmenities (String[] amenities)
    {
        this.amenities = amenities;
    }

    public String getTag ()
    {
        return tag;
    }

    public void setTag (String tag)
    {
        this.tag = tag;
    }

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    public String getPopularity_desc ()
    {
        return popularity_desc;
    }

    public void setPopularity_desc (String popularity_desc)
    {
        this.popularity_desc = popularity_desc;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

   
    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String[] getTypes ()
    {
        return types;
    }

    public void setTypes (String[] types)
    {
        this.types = types;
    }

    public String getDistrict ()
    {
        return district;
    }

    public void setDistrict (String district)
    {
        this.district = district;
    }

    public String getStar_rating ()
    {
        return star_rating;
    }

    public void setStar_rating (String star_rating)
    {
        this.star_rating = star_rating;
    }

    public String getNumber_of_rooms ()
    {
        return number_of_rooms;
    }

    public void setNumber_of_rooms (String number_of_rooms)
    {
        this.number_of_rooms = number_of_rooms;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [distance_from_search = "+distance_from_search+", hotel_id = "+hotel_id+", amenities = "+amenities+", tag = "+tag+", score = "+score+", popularity_desc = "+popularity_desc+", address = "+address+", name = "+name+",+ longitude = "+longitude+", latitude = "+latitude+", types = "+types+", district = "+district+", star_rating = "+star_rating+", number_of_rooms = "+number_of_rooms+", popularity = "+popularity+"]";
    }
}
			
			
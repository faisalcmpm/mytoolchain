package com.ibsplc.ndcapp.hotel.json;

public class HotelResponse {

	
	    private String image_host_url;

	    private String total_available_hotels;

	    private String last_update;

	    private Hotels_prices[] hotels_prices;

	    private Amenities[] amenities;

	    private String total_hotels;

	    private String status;

	    private Urls urls;

	    private Agents[] agents;

	    private String[] places;

	    private Debug_items[] debug_items;

	    private Hotels[] hotels;

	    public String getImage_host_url ()
	    {
	        return image_host_url;
	    }

	    public void setImage_host_url (String image_host_url)
	    {
	        this.image_host_url = image_host_url;
	    }

	    public String getTotal_available_hotels ()
	    {
	        return total_available_hotels;
	    }

	    public void setTotal_available_hotels (String total_available_hotels)
	    {
	        this.total_available_hotels = total_available_hotels;
	    }

	    public String getLast_update ()
	    {
	        return last_update;
	    }

	    public void setLast_update (String last_update)
	    {
	        this.last_update = last_update;
	    }

	    public Hotels_prices[] getHotels_prices ()
	    {
	        return hotels_prices;
	    }

	    public void setHotels_prices (Hotels_prices[] hotels_prices)
	    {
	        this.hotels_prices = hotels_prices;
	    }

	    public Amenities[] getAmenities ()
	    {
	        return amenities;
	    }

	    public void setAmenities (Amenities[] amenities)
	    {
	        this.amenities = amenities;
	    }

	    public String getTotal_hotels ()
	    {
	        return total_hotels;
	    }

	    public void setTotal_hotels (String total_hotels)
	    {
	        this.total_hotels = total_hotels;
	    }

	    public String getStatus ()
	    {
	        return status;
	    }

	    public void setStatus (String status)
	    {
	        this.status = status;
	    }

	    public Urls getUrls ()
	    {
	        return urls;
	    }

	    public void setUrls (Urls urls)
	    {
	        this.urls = urls;
	    }

	    public Agents[] getAgents ()
	    {
	        return agents;
	    }

	    public void setAgents (Agents[] agents)
	    {
	        this.agents = agents;
	    }

	    public String[] getPlaces ()
	    {
	        return places;
	    }

	    public void setPlaces (String[] places)
	    {
	        this.places = places;
	    }

	    public Debug_items[] getDebug_items ()
	    {
	        return debug_items;
	    }

	    public void setDebug_items (Debug_items[] debug_items)
	    {
	        this.debug_items = debug_items;
	    }

	    public Hotels[] getHotels ()
	    {
	        return hotels;
	    }

	    public void setHotels (Hotels[] hotels)
	    {
	        this.hotels = hotels;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [image_host_url = "+image_host_url+", total_available_hotels = "+total_available_hotels+", last_update = "+last_update+", hotels_prices = "+hotels_prices+", amenities = "+amenities+", total_hotels = "+total_hotels+", status = "+status+", urls = "+urls+", agents = "+agents+", places = "+places+", debug_items = "+debug_items+", hotels = "+hotels+"]";
	    }
	}

package com.ibsplc.ndcapp.carrental.json;

public class CarDetails
{
    
	private Cars[] cars;

    private Images[] images;

    private Websites[] websites;

    private Submitted_query submitted_query;

    private Car_classes[] car_classes;

    public Cars[] getCars ()
    {
        return cars;
    }

    public void setCars (Cars[] cars)
    {
        this.cars = cars;
    }

    public Images[] getImages ()
    {
        return images;
    }

    public void setImages (Images[] images)
    {
        this.images = images;
    }

    public Websites[] getWebsites ()
    {
        return websites;
    }

    public void setWebsites (Websites[] websites)
    {
        this.websites = websites;
    }

    public Submitted_query getSubmitted_query ()
    {
        return submitted_query;
    }

    public void setSubmitted_query (Submitted_query submitted_query)
    {
        this.submitted_query = submitted_query;
    }

    public Car_classes[] getCar_classes ()
    {
        return car_classes;
    }

    public void setCar_classes (Car_classes[] car_classes)
    {
        this.car_classes = car_classes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cars = "+cars+", images = "+images+", websites = "+websites+", submitted_query = "+submitted_query+", car_classes = "+car_classes+"]";
    }
}
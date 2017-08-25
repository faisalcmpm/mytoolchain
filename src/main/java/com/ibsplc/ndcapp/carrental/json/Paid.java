package com.ibsplc.ndcapp.carrental.json;

public class Paid
{
    
	private String currency_id;

    private String price;

    public String getCurrency_id ()
    {
        return currency_id;
    }

    public void setCurrency_id (String currency_id)
    {
        this.currency_id = currency_id;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [currency_id = "+currency_id+", price = "+price+"]";
    }
}
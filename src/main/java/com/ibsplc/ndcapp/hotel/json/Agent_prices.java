package com.ibsplc.ndcapp.hotel.json;


public class Agent_prices {
private String id;

private String price_total;

public String getId ()
{
return id;
}

public void setId (String id)
{
this.id = id;
}

public String getPrice_total ()
{
return price_total;
}

public void setPrice_total (String price_total)
{
this.price_total = price_total;
}

@Override
public String toString()
{
return "ClassPojo [id = "+id+", price_total = "+price_total+"]";
}
}
